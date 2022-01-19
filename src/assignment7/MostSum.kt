import kotlin.random.Random

fun main() {
    val random = Random(System.currentTimeMillis())
    val size = 100_000
//    val list = List(size) {
//        random.nextInt(-10_000, 10_000)
//    }.also {
//        println(it)
//    }
    val list = listOf(-2,1,-3,4,-1,2,1,-5,4)
    println(list.maxSublist())
}


fun List<Int>.maxSublist(): Int {
    var max = 0
    var current = max
    for (i in this.indices) {
        current += this[i]
        if (current > max) {
            max = current
        } else if (current < 0) {
            current = 0
        }
    }
    return max
}
