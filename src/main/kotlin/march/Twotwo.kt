package march

class Twotwo {
    fun pacificAtlantic(matrix: Array<IntArray>): List<List<Int>> {

        val reachablePoints = mutableListOf<List<Int>>()
        val cache = mutableMapOf<Pair<Int, Int>, Result>()

        matrix.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, v ->
                val reachable: Result = checkIfReachable(
                    rowIndex to columnIndex,
                    cache,

                    matrix,
                    mutableSetOf()
                )
                cache[rowIndex to columnIndex] = reachable
                if (reachable == Result.ALL)
                    reachablePoints.add(listOf(rowIndex, columnIndex))
            }
        }

        return reachablePoints
    }

    fun checkIfReachable(
        point: Pair<Int, Int>,
        cache: MutableMap<Pair<Int, Int>, Result>,
        matrix: Array<IntArray>,
        path: MutableSet<Pair<Int, Int>>
    ): Result {


        val m = matrix.size
        val n = matrix[0].size

        path.add(point)

        return determineOcean(point, m, n) ?: determineCache(point, cache)
        ?: findOutSurrounding(point, cache, matrix, path)
    }

    private fun determineCache(
        point: Pair<Int, Int>,
        cache: MutableMap<Pair<Int, Int>, Result>
    ): Result? = cache[point]

    private fun determineOcean(point: Pair<Int, Int>, m: Int, n: Int): Result? {
        return if (point.first == -1 || point.second == -1)
            Result.PA
        else if (point.first == m || point.second == n)
            Result.AT
        else null
    }

    private fun findOutSurrounding(
        point: Pair<Int, Int>,
        cache: MutableMap<Pair<Int, Int>, Result>,
        matrix: Array<IntArray>,
        path: MutableSet<Pair<Int, Int>>
    ): Result {
        val current = matrix.getValue(point)
        val movables = Movable.values()
        if (movables.isEmpty())
            return Result.NW
        val results = movables.map {

            val targetPoint = it.move(point)
            val targetPointValue = matrix.getValue(targetPoint)
            val availableMoveCondition =
                targetPointValue <= current && !path.contains(targetPoint)
            if (availableMoveCondition) checkIfReachable(
                targetPoint, cache, matrix, path
            ) else Result.NW
        }
        val merge = results.merge()
        return merge
    }

    private fun List<Result>.merge(): Result {

        if (this.contains(Result.ALL))
            return Result.ALL

        return if (this.contains(Result.AT))
            if (this.contains(Result.PA))
                Result.ALL
            else Result.AT
        else if (this.contains(Result.PA))
            Result.PA
        else Result.NW
    }

    private fun Array<IntArray>.getValue(point: Pair<Int, Int>): Int {

        if (point.first == -1 || point.second == -1)
            return -1
        if (point.first == this.size || point.second == this[0].size)
            return -1

        return this[point.first][point.second]
    }

    enum class Result {
        ALL, NW, PA, AT
    }

    enum class Movable {
        UP, DOWN, RIGHT, LEFT;

        fun move(point: Pair<Int, Int>) = when (this) {
            UP -> point.first - 1 to point.second
            DOWN -> point.first + 1 to point.second
            RIGHT -> point.first to point.second + 1
            LEFT -> point.first to point.second - 1
        }

    }
}