import java.util.*
import kotlin.collections.ArrayList

fun main() {
    Scanner(System.`in`).use { scan ->
        var sittingOrder = ""
        val names = ArrayList<String>(3)
        for (i in 0 .. 3) {
            val (name, move) = scan.nextLine().split(" ")
            if (move != "U") {
                names.add(name)
                sittingOrder += move
            }
        }
        val index = when(sittingOrder) {
            "RRL", "LLR" -> 0
            "RLR", "LRL" -> 0
//            "RRR", "LLL" -> 1
//            "RLL", "LRR" -> 1
            else -> 1
        }
        println(names[index])
    }
}
