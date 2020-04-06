import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import vasiliev.aleksey.ciphxor.Logic
import java.io.File
import kotlin.test.assertNotEquals


class Tests {
    @Test
    fun ifInputArgumentsAreCorrect() {
        assertEquals(Logic.argsParser(arrayOf("-c", "fffc", "input.txt")), listOf("fffc", "input.txt"))
        assertEquals(Logic.argsParser(arrayOf("-d", "1a0b2d", "input.txt", "-o", "output.txt")), listOf("1a0b2d", "input.txt", "output.txt"))
        assertNotEquals(Logic.argsParser(arrayOf("-c", "11bc", "input.txt", "-o", "output.txt")), listOf("1001", "input.txt", "output.txt"))
        assertThrows<org.apache.commons.cli.UnrecognizedOptionException> { Logic.argsParser(arrayOf("-ed", "11bccc", "input.txt", "-o", "output.txt")) }
    }

    @Test
    fun cipherChecking() {
        val howAreYouBefore = File("src/test/resources/HowAreYou.txt").readLines().joinToString("")
        Logic.cipher(listOf("10d", "src/test/resources/HowAreYou.txt", "src/test/resources/HowAreYou.txt"))
        Logic.cipher(listOf("10d", "src/test/resources/HowAreYou.txt", "src/test/resources/HowAreYou.txt"))
        val howAreYouAfter = File("src/test/resources/HowAreYou.txt").readLines().joinToString("")
        assertEquals(howAreYouBefore, howAreYouAfter)

        val alphabetBefore = File("src/test/resources/Alphabet.txt").readLines().joinToString("")
        Logic.cipher(listOf("fabacbcbdbbdbdbdab101020", "src/test/resources/Alphabet.txt", "src/test/resources/Alphabet.txt"))
        Logic.cipher(listOf("fabacbcbdbbdbdbdab101020", "src/test/resources/Alphabet.txt", "src/test/resources/Alphabet.txt"))
        val alphabetAfter = File("src/test/resources/Alphabet.txt").readLines().joinToString("")
        assertEquals(alphabetBefore, alphabetAfter)
    }
}