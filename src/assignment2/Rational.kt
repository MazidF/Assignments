package assignment2

import kotlin.math.abs

class Rational(numerator: Int = 0, denominator: Int = 1) {
    private val numerator: Int
    private val denominator: Int
    private val isPositive: Boolean
    init {
        if (denominator == 0) {
            throw Exception("denominator != 0")
        }
        isPositive = numerator * denominator >= 0
        val gcd = gcd(numerator, denominator)
        this.numerator = numerator / gcd
        this.denominator = denominator /gcd
    }

    fun add (num: Rational): Rational {
        return Rational(numerator * num.denominator + num.numerator * denominator, denominator * num.denominator)
    }

    fun sub (num: Rational): Rational {
        return Rational(numerator * num.denominator - num.numerator * denominator, denominator * num.denominator)
    }

    fun mul (num: Rational): Rational {
        return Rational(numerator * num.numerator, denominator * num.denominator)
    }

    fun div (num: Rational): Rational {
        return Rational(numerator * num.denominator, denominator * num.numerator)
    }

    fun toFloatingPoint(): Double {
        return numerator.toDouble() / denominator
    }

    private fun operation(): String {
        return if (isPositive) "" else "-"
    }

    override fun toString(): String {
        return operation() + "${abs(numerator)}/${abs(denominator)}"
    }

    fun toMakhlotString(): String {
        val operation = if (isPositive) "+" else "-"
        val numeratorABS = abs(numerator)
        val denominatorABS = abs(denominator)
        return if (numeratorABS < denominatorABS) {
            toString()
        } else {
            val int = numeratorABS / denominatorABS
            operation()  + "${numeratorABS - abs(denominatorABS) * int}/$denominatorABS $operation $int"
        }
    }
}

fun gcd(a: Int, b: Int) : Int {
    if (a == 0) return b
    return gcd(b % a, a)
}

fun main() {
    val num1 = Rational(-8, 6)
    println(num1.toFloatingPoint())
    println(num1.toString())
    println(num1.toMakhlotString())
    println(num1.add(Rational(10, 30)))
    println(num1.sub(Rational(9, 5)).toMakhlotString())
    println(num1.mul(Rational(3, -4)).toFloatingPoint())
    println(num1.div(Rational(7, -17)))
}
