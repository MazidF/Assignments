import kotlin.random.Random

fun main() {
    val random = Random(System.currentTimeMillis())
    val array = Array(10) {
        random.nextInt(0, 100)
    }
    val list = List(11) {
        random.nextInt(0, 100)
    }

    println("array : ${array.contentToString()}")
    println("array -> " + functionName(array))

    println("list : $list")
    println("list -> " + functionName(list))
}

fun functionName(array: Array<Int>) : List<Int> {
    return functionName(array.toList())
}

fun functionName(list: List<Int>) : List<Int> {
    val result = ArrayList<Int>()
    for (i in 0 until list.size / 2) {
        if (list[2 * i] <= list[2 * i + 1]) {
            result.add(list[2*i])
            result.add(list[2*i + 1])
        }
    }
    return result.toList()
}
