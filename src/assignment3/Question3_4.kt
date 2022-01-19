fun main() {
    println(functionName(19, 100, 13, 20, 14, 18, 22, 12, -100))
}

fun Int.abs() : Int {
    if (this < 0) return -this
    return this
}

fun functionName(vararg ints: Int): Pair<Int, Int> {
    var min = Int.MAX_VALUE
    var max = -Int.MAX_VALUE
    var minDifference = Int.MAX_VALUE

    for (i in ints.indices) {
        val int = ints[i]

        for (j in ints.indices) {
            if (j == i) continue
            val difference = (int - ints[j]).abs()
            if (difference < minDifference) {
                minDifference = difference
            }
        }

        if (min > int) {
            min = int
        }
        if (max < int) {
            max = int
        }
    }
    return Pair(max - min, minDifference)
}
