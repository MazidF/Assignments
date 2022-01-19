import java.util.*

class WasteOfTime

fun main() {
    Scanner(System.`in`).use { scan ->
        val arr = Array(4) {
            var max = scan.nextInt()
            for(i in 0 .. 1) {
                scan.nextInt().run {
                    if (this > max) {
                        max = this
                    }
                }
            }
            max
        }
        var index = 0
        var max = arr[index]
        for (i in 1 .. 3) {
            if (arr[i] > max) {
                index = i
                max = arr[index]
            }
        }
        println(index + 1)
    }
}
