import java.util.*
import kotlin.random.Random

fun main() {
    val tree1 = tree().fill(10)
    val tree2 = tree().fill(10)

    println(tree1.union(tree2))
    println(tree1.subscription(tree2))

}

typealias tree = TreeSet<Char>

val random = Random(System.currentTimeMillis())
const val a = 'a'.code
const val z = 'z'.code

fun tree.fill(size: Int, print: Boolean = true): tree {
    var c: Char
    var counter = 0
    while (counter < size) {
        c = random.nextInt(a, z).toChar()
        if (c !in this) {
            this.add(c)
            counter++
        }
    }
    if (print) {
        println(this)
    }
    return this
}


fun tree.union(other: tree) : tree {
    val result = tree()

    result.addAll(this)
    result.addAll(other)

    return result
}


fun tree.subscription(other: tree) : tree {
    val result = tree()

    val filteredList = this.filter { s ->
        s in other
    }
    result.addAll(filteredList)

    return result
}
