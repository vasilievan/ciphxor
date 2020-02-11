import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test


class Tests {
    @Test
    fun ifInputArgumentsAreCorrect() {
        assertTrue(checkIfArgumentsAreCorrect(arrayOf("-c", "fffc", "input.txt")), "Illegal args: " + arrayOf("-c", "fffc", "input.txt").joinToString(" "))
        assertTrue(checkIfArgumentsAreCorrect(arrayOf("-d", "102bbff900dc", "input.txt", "-o", "output.txt")), "Illegal args: " + arrayOf("-d", "102bbff900dc", "input.txt", "-o", "output.txt").joinToString(" "))

        assertFalse(checkIfArgumentsAreCorrect(arrayOf("-h", "mnd0kfdo", "input.txt")), "Illegal args: " + arrayOf("-h", "mnd0kfdo", "input.txt").joinToString(" "))
        assertFalse(checkIfArgumentsAreCorrect(arrayOf("-d", "10f92bac", "input.txt", "-l", "output.txt", "-r")), "Illegal args: " + arrayOf("-d", "10f92bac", "input.txt", "-l", "output.txt", "-r").joinToString(" "))
        assertFalse(checkIfArgumentsAreCorrect(arrayOf("-d", "10f92bac", "input.txt", "output.txt")), "Illegal args: " + arrayOf("-d", "10f92bac", "input.txt", "output.txt").joinToString(" "))
    }
    @Test
    fun ifBinaryIsCorrect() {
        assertEquals("111111111111", "fff".hexStringToBinary())
        assertEquals("101010111100", "abc".hexStringToBinary())

        assertNotEquals("1001", "0".hexStringToBinary())
        assertNotEquals("110101010101", "fff".hexStringToBinary())
    }

    @Test
    fun cipherChecking() {
        assertEquals("0001011 1011111 0011011 101101 0100010 1000010 0001001 101101 0111010 1011111 0011001 110010 ", cipher("10d", "testFiles/HowAreYou.txt", "nothing"))
        assertEquals("0100011 1011001 1001100 0000010 1111000 1000100 0110110 1100010 ", cipher("189abcd4653532", "testFiles/Alphabet.txt", "nothing"))
    }

    @Test
    fun decipherChecking() {
        assertEquals("Hello, Kotlin!", decipher("f0f", "testFiles/HelloKotlin.txt", "nothing"))
        assertEquals("Love programming!", decipher("abc", "testFiles/LoveProgramming.txt", "nothing"))
    }
}