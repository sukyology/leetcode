package march

internal class TwotwoTest {

    @org.junit.jupiter.api.Test
    fun pacificAtlantic() {

        val list = Twotwo().pacificAtlantic(
            arrayOf(
                arrayOf(1, 2, 2, 3, 5).toIntArray(),
                arrayOf(3, 2, 3, 4, 4).toIntArray(),
                arrayOf(2, 4, 5, 3, 1).toIntArray(),
                arrayOf(6, 7, 1, 4, 5).toIntArray(),
                arrayOf(5, 1, 1, 2, 4).toIntArray()
            )
        )

        println(list)
    }

    @org.junit.jupiter.api.Test
    fun checkIfReachable() {
        val result = Twotwo().checkIfReachable(
            point = 1 to 3, cache = mutableMapOf(), matrix = arrayOf(
                arrayOf(1, 2, 2, 3, 5).toIntArray(),
                arrayOf(3, 2, 3, 4, 4).toIntArray(),
                arrayOf(2, 4, 5, 3, 1).toIntArray(),
                arrayOf(6, 7, 1, 4, 5).toIntArray(),
                arrayOf(5, 1, 1, 2, 4).toIntArray()
            ),
            mutableSetOf()
        )

        println(result)
    }
}