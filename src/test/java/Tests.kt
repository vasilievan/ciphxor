import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import vasiliev.aleksey.ciphxor.Logic
import java.io.File


/**class Tests {
    @Test
    fun ifInputArgumentsAreCorrect() {
        assertTrue(Logic.checkIfArgumentsAreCorrect(arrayOf("-c", "fffc", "input.txt")), "Illegal args: " + arrayOf("-c", "fffc", "input.txt").joinToString(" "))
        assertTrue(Logic.checkIfArgumentsAreCorrect(arrayOf("-d", "102bbff900dc", "input.txt", "-o", "output.txt")), "Illegal args: " + arrayOf("-d", "102bbff900dc", "input.txt", "-o", "output.txt").joinToString(" "))

        assertFalse(Logic.checkIfArgumentsAreCorrect(arrayOf("-h", "mnd0kfdo", "input.txt")), "Illegal args: " + arrayOf("-h", "mnd0kfdo", "input.txt").joinToString(" "))
        assertFalse(Logic.checkIfArgumentsAreCorrect(arrayOf("-d", "10f92bac", "input.txt", "-l", "output.txt", "-r")), "Illegal args: " + arrayOf("-d", "10f92bac", "input.txt", "-l", "output.txt", "-r").joinToString(" "))
        assertFalse(Logic.checkIfArgumentsAreCorrect(arrayOf("-d", "10f92bac", "input.txt", "output.txt")), "Illegal args: " + arrayOf("-d", "10f92bac", "input.txt", "output.txt").joinToString(" "))
    }

    @Test
    fun cipherChecking() {
        val howAreYouBefore = File("testFiles/HowAreYou.txt").readLines().joinToString("")
        Logic.cipher("10d", "testFiles/HowAreYou.txt", "nothing")
        Logic.cipher("10d", "testFiles/HowAreYou.txt", "nothing")
        val howAreYouAfter = File("testFiles/HowAreYou.txt").readLines().joinToString("")
        assertEquals(howAreYouBefore, howAreYouAfter)

        val alphabetBefore = File("testFiles/Alphabet.txt").readLines().joinToString("")
        Logic.cipher("fabacbcbdbbdbdbdab101020", "testFiles/Alphabet.txt", "nothing")
        Logic.cipher("fabacbcbdbbdbdbdab101020", "testFiles/Alphabet.txt", "nothing")
        val alphabetAfter = File("testFiles/Alphabet.txt").readLines().joinToString("")
        assertEquals(alphabetBefore, alphabetAfter)
    }
}**/