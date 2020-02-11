import java.io.File
import java.lang.StringBuilder

fun main (args: Array<String>) {
    if (!checkIfArgumentsAreCorrect(args)) {
        // user-friendly интерфейс
        println("Incorrect arguments.")
        return
    }
    var outputPath = "nothing"
    if (args.size == 5) {
        outputPath = args[4]
    }
    if (args[0] == "-c") {
        cipher(args[1], args[2], outputPath)
        return
    }
    decipher(args[1], args[2], outputPath)
}

// проверка исходных аргументов на корректность. Ограничение в длине имени файла и используемых символах
// обусловелено требованиями ОС Windows
fun checkIfArgumentsAreCorrect(args: Array<String>): Boolean = (args.joinToString(" "))
        .matches(Regex("""-[cd] [0-9a-fA-F]+ [^\\/:*?<>|.]{0,220}\.txt( -o [^\\/:*?<>|]{0,220}\.txt)?"""))

// шифратор
fun cipher(key: String, inputPath: String, outputPath: String): String {
    val inputFile = File(inputPath).bufferedReader().readLines()
    val binaryKey = key.hexStringToBinary()
    var currentPosition = 0
    val sb = StringBuilder()
    for (line in inputFile) {
        for (symbol in line) {
            for (bit in symbol.toInt().toString(2)) {
                if (currentPosition == binaryKey.length) {
                    currentPosition = 0
                }
                sb.append(if (bit == binaryKey[currentPosition]) "0" else "1")
                currentPosition++
            }
            sb.append(" ")
        }
    }
    var path = outputPath
    if (path == "nothing") {
        path = inputPath
    }
    val outputFile = File(path).bufferedWriter()
    outputFile.append(sb.toString())
    outputFile.close()
    println("Encrypted successfully!")
    return sb.toString()
}

fun decipher(key: String, inputPath: String, outputPath: String): String {
    val inputFile = File(inputPath).bufferedReader().readLines()
    val binaryKey = key.hexStringToBinary()
    var currentPosition = 0
    val mySb = StringBuilder()
    for (line in inputFile) {
        for (word in line.split(" ")) {
            val sb = StringBuilder()
            for (bit in word) {
                if (currentPosition == binaryKey.length) {
                    currentPosition = 0
                }
                sb.append(if (bit == binaryKey[currentPosition]) "0" else "1")
                currentPosition++
            }
            if (sb.isNotEmpty()) {
                mySb.append(sb.toString().toInt(2).toChar())
            }
        }
    }
    var path = outputPath
    if (path == "nothing") {
        path = inputPath
    }
    val outputFile = File(path).bufferedWriter()
    outputFile.append(mySb.toString())
    outputFile.close()
    println("Decrypted successfully!")
    return mySb.toString()
}

// обработка шестнадцатеричной строки неограниченной длины. Ны выходе получаем бинарную строку.
fun String.hexStringToBinary(): String = this.toBigInteger(16).toString(2)