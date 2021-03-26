package march

class TwoThree {

    fun wordSubsets(A: Array<String>, B: Array<String>): List<String> {
        val bMap = B.map { stringToMap(it) }
        val total = mutableMapOf<Char, Int>()
        var a = 'a'
        while (a <= 'z') {

            val max = bMap.maxOf{ it[a] ?: 0 }
            if (max > 0)
                total[a] = max
            ++a
        }
        val aMap = A.map { it to stringToMap(it) }
        return aMap.filter {
            total.all { entry ->
                it.second[entry.key] ?: 0 >= entry.value
            }
        }.map { it.first }
    }

    fun stringToMap(string: String): Map<Char, Int> {
        val map = mutableMapOf<Char, Int>()

        string.forEach {
            val v = map[it]
            if (v != null)
                map[it] = v + 1
            else
                map[it] = 1
        }
        return map
    }

    public inline fun <T, R : Comparable<R>> Iterable<T>.maxOf(selector: (T) -> R): R {
        val iterator = iterator()
        if (!iterator.hasNext()) throw NoSuchElementException()
        var maxValue = selector(iterator.next())
        while (iterator.hasNext()) {
            val v = selector(iterator.next())
            if (maxValue < v) {
                maxValue = v
            }
        }
        return maxValue
    }
}

