import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    var current: Int
    var counter = 0
    var last: Int = input.nextInt()
    for (i in 1 until n) {
        current = input.nextInt()
        if (current != last) counter++
        last = current
    }
    println(counter)
}
