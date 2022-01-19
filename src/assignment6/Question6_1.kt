import java.math.BigInteger
import java.util.*
import java.util.regex.Pattern

fun main() {
    println(decimalToBinary("12"))
    println(binaryToDecimal(decimalToBinary("12343.43253")))
    println()
    println(binaryToDecimal("111.01"))
    println(decimalToBinary(binaryToDecimal("111.01")))
}

val regex1: Pattern = Pattern.compile("^\\d*(\\.\\d*)?$")
val regex2: Pattern = Pattern.compile("^[01]*(\\.[01]*)?$")

fun decimalToBinary(input: String, floatLength: Int = 10) : String {
    val matcher = regex1.matcher(input)
    if (!matcher.find()) {
        return "input is Invalid!!!"
    }

    var (int, float) = input.split(".").run {
        if(this.size == 1) {
            listOf(this[0], 0)
        } else {
            this
        }
    }

    var big = BigInteger(int.toString())
    val stack = Stack<String>()
    val two = BigInteger("2")

    while (big.intValueExact() != 0) {
        big.divideAndRemainder(two).run {
            big = this[0]
            stack.push(this[1].toString())
        }
    }
    int = stack.reversed().joinToString("")

    stack.clear()
    val str = float.toString()
    var result = "0.$str".toDouble()
    for (i in 0 .. floatLength) {
//        result = (result * 2)
        result *= 2
        if (result >= 1) {
            stack.push("1")
            result -= 1
        } else {
            stack.push("0")
        }

    }
    float = stack.joinToString("")

    return "$int.$float"
}

fun binaryToDecimal(input: String) : String {
    val matcher = regex2.matcher(input)
    if (!matcher.find()) {
        return "input is Invalid!!!"
    }

    var (int, float) = input.split(".").run {
        if(this.size == 1) {
            listOf(this[0], 0)
        } else {
            this
        }
    }

    var str = int.toString()
    var big = BigInteger("0")
    val one = BigInteger("1")
    val two = BigInteger("2")
    for (c in str) {
        big = big.multiply(two)
        if (c == '1') {
            big = big.add(one)
        }
    }
    int = big.toString()

    str = float.toString().reversed()
    val half = 0.5
    float = 0.0
    for (c in str) {
        float *= half
        if (c == '1') {
            float += half
        }
    }
    return "$int${float.toString().substring(1)}"
}
