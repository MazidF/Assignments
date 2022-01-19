import java.util.*

fun main() {
    var list = listOf("flower","flow","flight")
    var min = Int.MAX_VALUE
    list = list.sortedBy {
        if (it.length < min) {
            min = it.length
        }
        it
    }

    val result = StringBuilder()
    var i = 0
    val first = list.first()
    val last = list.last()
    while (i < min) {
        if (first[i] == last[i]) {
            result.append(first[i])
        } else break
        i++
    }

    println("\"${result}\"")
}
