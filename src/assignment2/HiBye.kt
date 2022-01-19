import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val size = input.nextInt()
    val names = Array(size) {input.next()}
    for(i in 0 until size) {
        val name = names[i]
        for(j in i-1 downTo 0) {
            println("$name: salam ${names[j]}!")
        }
    }
    for(i in 0 until size) {
        val name = names[i]
        println("$name: khodafez bacheha!")
        for(j in i+1 until size) {
            println("${names[j]}: khodafez $name!")
        }
    }
}
