fun main() {
    val input = readLine()!!
    val size = input.length
    for (i in 0 until size / 2) {
        if (input[i] != input[size - 1 - i]) {
            println("NO")
            return
        }
    }
    println("YES")
}
