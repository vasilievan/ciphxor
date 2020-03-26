package vasiliev.aleksey.ciphxor

import org.apache.commons.cli.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.lang.StringBuilder
import java.util.stream.Stream
import kotlin.experimental.xor
import kotlin.streams.toList

object Logic {
    fun argsParser(args: Array<String>): List<String> {
        val summaryOptions = Options()

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

        val defaultParser = DefaultParser()
        val parsedCmdLine = defaultParser.parse(summaryOptions, args)
        val parsedArguments = mutableListOf<String>()
        if (parsedCmdLine.hasOption("c")) {
            parsedArguments.add(parsedCmdLine.getOptionValues("c")[0])
            parsedArguments.add(parsedCmdLine.getOptionValues("c")[1])
        } else if (parsedCmdLine.hasOption("d")) {
            parsedArguments.add(parsedCmdLine.getOptionValues("d")[0])
            parsedArguments.add(parsedCmdLine.getOptionValues("d")[1])
        }
        if (parsedCmdLine.hasOption("o")) {
            parsedArguments.add(parsedCmdLine.getOptionValues("o")[0])
        }
        return parsedArguments
    }

    fun cipher(list: List<String>) {
        if (list.size < 2) {
            println("Incorrect arguments.")
            return
        }
        try {
            var currentPosition = 0L
            val inputFile = FileInputStream(list[1])
            val inputFileBytes = inputFile.readBytes()
            inputFile.close()
            val outputFile = if (list.size > 2) FileOutputStream(list[2]) else FileOutputStream("modified_" + list[1])
            val binaryKey = list[0].hexStringToBinary()
            for (byte in inputFileBytes) {
                val sb = StringBuilder()
                sb.append(binaryKey.chars().skip(currentPosition).limit(8L).toList().map { it.toChar() }.joinToString (""))
                currentPosition += 8L
                currentPosition %= binaryKey.length
                if (sb.length < 8) {
                    sb.append(binaryKey.chars().limit(currentPosition).toList().map { it.toChar() }.joinToString (""))
                }
                outputFile.write((byte xor sb.toString().toInt(2).toByte()).toInt())
            }
            outputFile.close()
        } catch (e: IOException) {
            println("File not found.")
        }
    }

    private fun String.hexStringToBinary(): String = this.toBigInteger(16).toString(2)
}