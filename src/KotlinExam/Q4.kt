package KotlinExam

fun main() {
    println(function4("MCMXCIV"))
    println(function4("LVIII"))
    println(function4("III"))
}

val map = mapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50, "C" to 100, "D" to 500, "M" to 1000)

fun function4(input: String): Int {
    val list = input.map {
        "$it"
    }
    var result = 0
    var i = 1
    var current: Int
    var last: Int = map[list[0]]!!

    while (i < input.length) {
        current = map[list[i]] ?: throw Exception("Invalid Input!!")
        if (last < current) {
            result += (current - last)
            last = if (i + 1 != input.length) {
                map[list[++i]] ?: throw Exception("Invalid Input!!")
            } else {
                0
            }
        } else {
            result += last
            last = current
        }
        i++
    }

    return result + last
}
