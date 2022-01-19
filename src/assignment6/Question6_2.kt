import java.util.*
import kotlin.collections.ArrayList

fun main() {
    Scanner(System.`in`).use { scan ->
        val n = scan.nextInt()
        val list = ArrayList<P>(100) // maximum time is 100s.
        var p: P
        for (i in 0 until 25) {
            p = P(2 * i, 2 * i)
            list.add(p)
            list.add(p + P(1, 1))
            list.add(p + P(2, 0))
            list.add(p + P(3, 1))
        }
        for (i in 0 until n) {
            p = P(scan.nextInt(), scan.nextInt())
            println("${list.indexOf(p)} , ${checker(p)}")
        }
    }
}

fun checker(p: P): Int {
    val (x, y) = p
    //   2   4
    // 1   3
    return when {
        x == y && x % 2 == 0 -> x + y         // 1
        x == y && x % 2 == 1 -> x + y - 1     // 2
        x == y + 2 && x % 2 == 0 -> x + y     // 3
        x == y + 2 && x % 2 == 1 -> x + y - 1 // 4
        else -> -1
    }
}

typealias P = Pair<Int, Int>

operator fun P.plus(other: P): P {
    return P(first + other.first, second + other.second)
}
