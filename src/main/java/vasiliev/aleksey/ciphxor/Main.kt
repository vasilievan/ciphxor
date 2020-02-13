package vasiliev.aleksey.ciphxor

/**
 * This app was created as a part of the course project.
 * Its main function is to cipher and decipher bytes from text files (.txt).
 * ciphxor.jar file may be opened using such command in cmd:
 * java -jar ciphxor.jar -c [-d] hexidecimalKey inputPath.txt [-o outputPath.txt]
 * Helping class Logic.kt has three functions:
 *
 * checkIfArgumentsAreCorrect to check if supplied arguments are correct. It uses
 * Regex for checking. Note that .txt file is supposed to be correct, because you can
 * create file with such name in Windows. Briefly, all possible names for the files in Windows OS
 * are supported.
 *
 * cipher to encrypt and decrypt. It uses XOR from kotlin library.
 *
 * hexStringToBinary to present hexidecimal key in binary string (unlimited length).
 *
 * As you might notice, an algoritm uses only one function for the both operations.
 * That's a feature of XOR-algoritm.
 *
 * @author      <a href="mailto:vasiliev.an@edu.spbstu.ru">Aleksei Vasilev</a>
 * @version     1.1
 * @since       1.1
 */

fun main (args: Array<String>) {
    if (!Logic.checkIfArgumentsAreCorrect(args)) {
        // user-friendly interface
        println("Incorrect arguments.")
        return
    }
    var outputPath = "nothing"
    if (args.size == 5) {
        outputPath = args[4]
    }
    Logic.cipher(args[1], args[2], outputPath)
    if (args[0] == "-c") {
        println("Encrypted successfully!")
    } else {
        println("Decrypted successfully!")
    }
}