import java.util.*
import kotlin.math.abs

fun main() {
    val scanner = Scanner(System.`in`)
    val k = scanner.nextInt()
    var a = scanner.nextInt()
    var b = scanner.nextInt()
    if (b < a) {
        val temp = a
        a = b
        b = temp
    }
    val firstStation = ((a / k) * k).run {  // this <= a <= this + k
        if (a - this + 1 < (this + k) - a) {
            this
        } else {
            this + k
        }
    }
    val secondStation = ((b / k) * k).run {  // this <= b <= this + k
        if (b - this < (this + k) - b + 1) {
            this
        } else {
            this + k
        }
    }
    println((abs(a - firstStation)) + (secondStation - firstStation) / k  + abs(b - secondStation))
            // time to first station   +      time between 2 stations        +  time to destination
}
