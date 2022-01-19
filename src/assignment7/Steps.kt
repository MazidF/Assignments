fun main() {
    val int = readLine()!!.toInt()
    var start = System.currentTimeMillis()
    println("result: " + step(int))
    println("time in millisecond: " + (System.currentTimeMillis() - start))

    start = System.currentTimeMillis()
    println("result: " + step2(int))
    println("time in millisecond: " + (System.currentTimeMillis() - start))
}

val map = hashMapOf(1 to 1, 2 to 2)

fun step(n: Int) : Int {
    val result = map[n] ?: run {
        (step(n - 1) + step(n - 2)).also {
            map[n] = it
        }
    }
    return result
}

fun step2(n: Int) : Int {
    if (n <= 2) return n
    return step2(n - 2) + step2(n - 1)
}
