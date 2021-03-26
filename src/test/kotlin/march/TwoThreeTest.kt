package march

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TwoThreeTest {

    @Test
    fun wordSubsets() {
        val universe = TwoThree().wordSubsets(
            arrayOf("amazon"),
            arrayOf("e", "o")
        )

        println(universe)
    }

    @Test
    fun stringToMap() {
    }
}