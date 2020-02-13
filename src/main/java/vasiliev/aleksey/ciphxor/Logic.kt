package vasiliev.aleksey.ciphxor

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import kotlin.experimental.xor

object Logic {
    fun checkIfArgumentsAreCorrect(args: Array<String>): Boolean = (args.joinToString(" "))
            .matches(Regex("""-[cd] [0-9a-fA-F]+ \S*\.txt( -o \S*\.txt)?"""))

    fun cipher(key: String, inputPath: String, outputPath: String) {
        var path = outputPath
        if (path == "nothing") {
            path = inputPath
        }
        try {
            var currentPosition = 0
            val inputFile = FileInputStream(inputPath).readBytes()
            val outputFile = FileOutputStream(path)
            val binaryKey = if ((inputFile.isNotEmpty()) && (key.hexStringToBinary().length / (inputFile.size * 7) >= 1)) key.hexStringToBinary()
            else (key.hexStringToBinary()).repeat((inputFile.size * 7) / key.hexStringToBinary().length + 1)
            for (byte in inputFile) {
                outputFile.write((byte xor binaryKey.subSequence(currentPosition, currentPosition + 7).toString().toInt(2).toByte()).toInt())
                currentPosition += 7
            }
        } catch (e: IOException) {
            println("File not found.")
        }
    }

    private fun String.hexStringToBinary(): String = this.toBigInteger(16).toString(2)
}