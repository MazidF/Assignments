package KotlinExam

fun main() {
    println(function1(arrayOf(2, 4, 32, 4, 24, 1), 100))
    println(function1(arrayOf(2, 4, 32, 4, 24, 1), 56))
}

typealias P = Pair<Int, Int>

fun function1(arr: Array<Int>, target: Int) : P {
    var value: Int
    for (i in arr.indices) {
        value = arr[i]
        for (j in i + 1 until arr.size) {
            if (value + arr[j] == target) {
                return P(i, j)
            }
        }
    }
    return P(-1, -1)
}
