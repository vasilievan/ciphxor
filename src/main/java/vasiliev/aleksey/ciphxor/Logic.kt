package vasiliev.aleksey.ciphxor

import org.apache.commons.cli.*
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.lang.StringBuilder
import kotlin.experimental.xor
import kotlin.streams.toList

object Logic {
    private val summaryOptions = Options()
    fun argsParser(args: Array<String>): List<String> {
        setUp()
        val defaultParser = DefaultParser()
        val parsedCmdLine = defaultParser.parse(summaryOptions, args)
        val parsedArguments = mutableListOf<String>()
        if (parsedCmdLine.hasOption("c")) {
            parsedArguments += parsedCmdLine.getOptionValues("c")
        } else if (parsedCmdLine.hasOption("d")) {
            parsedArguments += parsedCmdLine.getOptionValues("d")
        }
        if (parsedCmdLine.hasOption("o")) {
            parsedArguments.add(parsedCmdLine.getOptionValue("o"))
        }
        return parsedArguments
    }

    private fun setUp() {
        val cipherOption = Option("c", "cipher", true, "It's used to cipher the file. Put key as an argument and input file name.")
        cipherOption.args = 2
        cipherOption.setOptionalArg(false)
        cipherOption.argName = "cipher "

        val decipherOption = Option("d", "decipher", true, "It's used to decipher the file. Put key as an argument and input file name.")
        decipherOption.args = 2
        decipherOption.setOptionalArg(false)
        decipherOption.argName = "decipher "

        val outputOption = Option("o", "output", true, "Output file name.")

        val cdOptions = OptionGroup()
        cdOptions.addOption(cipherOption)
        cdOptions.addOption(decipherOption)

        summaryOptions.addOptionGroup(cdOptions)
        summaryOptions.addOption(outputOption)
    }

    fun cipher(list: List<String>) {
        if (list.size < 2) {
            println("Incorrect arguments.")
            return
        }
        var currentPosition = 0L
        try {
            FileInputStream(list[1]).use { input ->
                val inputFileBytes = input.readBytes()
                input.close()
                val outputFile = if (list.size > 2) FileOutputStream(list[2]) else FileOutputStream("modified_" + list[1])
                outputFile.use { out ->
                    val binaryKey = list[0].hexStringToBinary()
                    for (byte in inputFileBytes) {
                        val sb = StringBuilder()
                        sb.append(binaryKey.chars().skip(currentPosition).limit(8L).toList().map { it.toChar() }.joinToString(""))
                        currentPosition += 8L
                        currentPosition %= binaryKey.length
                        if (sb.length < 8) {
                            sb.append(binaryKey.chars().limit(currentPosition).toList().map { it.toChar() }.joinToString(""))
                        }
                        out.write((byte xor sb.toString().toInt(2).toByte()).toInt())
                    }
                    out.close()
                }
            }
        } catch (ex: FileNotFoundException) {
            println("File not found. Check if path is correct.")
        }
    }

    private fun String.hexStringToBinary(): String = this.toBigInteger(16).toString(2)
}