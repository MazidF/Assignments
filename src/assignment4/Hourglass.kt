import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val array = Array(n) {
        Array(n) {0}
    }
    val list = Array(n) {
        MutableList(n - 2) {0}
    }

    var a: Int
    var b: Int
    var c: Int
    var d: Int
    for (i in 0 until n) {
        val arr = array[i]
        val lst = list[i]
        a = scanner.nextInt().also { arr[0] = it }
        b = scanner.nextInt().also { arr[1] = it }
        c = scanner.nextInt().also { arr[2] = it }
        lst[0] = a + b + c
        for (j in 3 until n) {
            d = scanner.nextInt().also { arr[j] = it }
            lst[j - 2] = lst[j - 3] + d - a
            a = b
            b = c
            c = d
        }
    }
    println(array.map(Array<Int>::contentToString))
    println(list.contentToString())
    var max = -Int.MAX_VALUE
    for (i in 0 until (n - 2)) {
        val arr = array[i + 1]
        for (j in 0 until (n - 2)) {
            a = list[i][j] + arr[j + 1] + list[i + 2][j]
            if (max < a) {
                max = a
            }
        }
    }
    println("max: $max")
}
//9
//6 -1 3 0 -3 7 -9 4 5
//-1 7 -6 2 -6 2 6 -3 6
//-8 -4 -3 5 -9 6 -1 -8 -8
//3 2 -3 2 -2 -7 0 -9 -8
//6 -5 6 -6 3 2 -4 -1 3
//7 4 6 -4 -6 3 4 2 3
//4 9 0 -2 -3 7 -3 -8 -6
//1 -5 -1 4 -1 -3 0 2 -8
//-4 4 -6 4 3 -7 -2 5 -2
