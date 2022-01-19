package assignment1

import kotlin.math.pow

class MyClock(hour: Int? = null, minute: Int? = null, second: Int? = null) {
    private var hour = 0
    set(value) {
        if (value in 0..23) {
            field = value
        } else {
            println("Invalid value to set for hour")
        }
    }

    private fun getHour() : Int {
        return if (is12Hours) {
            hour % 12
        } else {
            hour
        }
    }

    private var minute = 0
        set(value) {
            if (value in 0..59) {
                field = value
            } else {
                println("Invalid value to set for minute")
            }
        }

    private var second = 0
        set(value) {
            if (value in 0..59) {
                field = value
            } else {
                println("Invalid value to set for second")
            }
        }

    private var is12Hours = false

    init {
        setClock(hour, minute, second)
    }

    fun setClock(hour: Int? = null, minute: Int? = null, second: Int? = null) {
        hour?.let {
            this.hour = it
        }
        minute?.let {
            this.minute = it
        }
        second?.let {
            this.second = it
        }
    }

    fun digitalFormat() {
        var result = String.format("%02d:%02d:%02d", getHour(), minute, second)
        if (is12Hours) {
            result += (if (hour >= 12) "  PM" else "  AM")
        }
        println(result)
    }

    fun get(clockValue: ClockValue): Int {
        return clockValue.get(this)
    }

    fun set12Hour(isTrue: Boolean) {
        is12Hours = isTrue
    }

    fun addOneUnit(clockValue: ClockValue) {
        clockValue.addOneUnit(this)
    }

    // using enum for practicing :)
    enum class ClockValue {
        HOUR {
            override fun get(myClock: MyClock) = myClock.hour
            override fun addOneUnit(myClock: MyClock) {
                myClock.hour = (myClock.hour + 1) % 24
            }
        },
        MINUTE {
            override fun get(myClock: MyClock) = myClock.minute
            override fun addOneUnit(myClock: MyClock) {
                if (myClock.minute == 59) {
                    HOUR.addOneUnit(myClock)
                }
                myClock.minute = (myClock.minute + 1) % 60
            }
        },
        SECOND {
            override fun get(myClock: MyClock) = myClock.second
            override fun addOneUnit(myClock: MyClock) {
                if (myClock.second == 59) {
                    MINUTE.addOneUnit(myClock)
                }
                myClock.second = (myClock.second + 1) % 60
            }
        };
        abstract fun get(myClock: MyClock): Int
        abstract fun addOneUnit(myClock: MyClock)
    }

    fun equals(myClock: MyClock): Boolean {
        // saving state of is12Hour of each object
        val is12This = this.is12Hours
        val is12MyClock = myClock.is12Hours

        this.is12Hours = true
        myClock.is12Hours = true

        val result = (this.hour == myClock.hour) &&
                (this.minute == myClock.minute) &&
                (this.second == myClock.second)

        this.is12Hours = is12This
        myClock.is12Hours = is12MyClock

        return result
    }
}


class MyCalculator {
    private var lastResult: Double = 0.0 // default of calculators :)
    set(value) {
        println(String.format("%.3f", value))
        field = value
    }

    private fun cal(num1: Number, num2: Number? = null, task: (Double, Double) -> Double) : MyCalculator {
        num2?.let {
            lastResult = task(num1.toDouble(), num2.toDouble())
            return this
        }
        lastResult = task(lastResult, num1.toDouble())
        return this
    }

    fun sum(num1: Number, num2: Number? = null) = cal(num1, num2) { a, b -> a + b }

    fun sub(num1: Number, num2: Number? = null) = cal(num1, num2) { a, b -> a - b}

    fun multi(num1: Number, num2: Number? = null) = cal(num1, num2) { a, b -> a * b}

    fun divide(num1: Number, num2: Number? = null) = cal(num1, num2) { a, b -> a / b}

    fun pow(num1: Number, num2: Number? = null) = cal(num1, num2) { a, b -> a.pow(b)}

    fun reset() : MyCalculator {
        lastResult = 0.0
        return this
    }

    // static method :)
    // please enter a good input
    companion object {
        fun calculator(input: String): Float? {
            val numbers = ArrayList<Float>()
            val operators = ArrayList<Operation>()
            val number = StringBuilder()
            var c: Char
            var isNumber: Boolean
            var index = 0
            if (input[0] == '-') {
                numbers.add(0f)
            }
            try {
                while (index < input.length) {
                    c = input[index]
                    isNumber = false
                    when (c) {
                        ' ' -> {
                            index++
                            continue
                        }
                        '+', '-', '/', '*', '^' -> {
                            operators.add(
                                when (c) {
                                    '+' -> Operation.Sum
                                    '-' -> Operation.Sub
                                    '*' -> Operation.Multi
                                    '/' -> Operation.Div
                                    '^' -> Operation.Pow
                                    else -> throw Exception("$c is invalid value!!!")
                                }
                            )
                        }
                        '(' -> {
                            var counter = 0
                            var end = index + 1
                            while (!(input[end] == ')' && counter == 0)) {
                                if (input[end] == '(') counter++
                                if (input[end] == ')') counter--
                                end++
                            }
                            numbers.add(calculator(input.substring(index + 1, end)) ?: return null)
                            index = end
                        }
                        ')' -> {
                            throw Exception(") is redundant!!!")
                        }
                        else -> {
                            number.append(c)
                            isNumber = true
                        }
                    }
                    if (!isNumber && number.isNotEmpty()) {
                        numbers.add(number.toString().toFloat())
                        number.clear()
                    }
                    index++
                }
                if (number.toString().isNotEmpty()) {
                    numbers.add(number.toString().toFloat())
                }
                if (operators.size >= numbers.size) throw Exception(operators.toString() + "\n" + numbers.toString())
                for (ord in 4 downTo -1) {
                    var i = 0
                    while (i < operators.size) {
                        if (operators[i].ordinal == ord) {
                            if (i == 1 && numbers.size == 1) println()
                            numbers[i] = operators[i].cal(numbers[i], numbers[i + 1])
                            operators.removeAt(i)
                            numbers.removeAt(i + 1)
                            i--
                        }
                        i++
                    }
                }
                return numbers[0]
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                println("Input does not match the requested format")
            }
            return null
        }

        fun live() {
            println("welcome to live method.\n---> enter 'exit' when you done")
            var a: String
            while (true) {
                a = readLine()!!
                if (a == "exit") break
                try {
                    println(calculator(a) ?: continue)
                } catch (e: Exception) {
                    println("something is wrong.\nplease try again")
                }
            }
            println("end of live method.\n")
        }
    }
}


enum class Operation() {
    Sum {
        override fun cal(a: Float, b: Float): Float {
            return a + b
        }
    },
    Sub {
        override fun cal(a: Float, b: Float): Float {
            return a - b
        }
    },
    Multi {
        override fun cal(a: Float, b: Float): Float {
            return a * b
        }
    },
    Div {
        override fun cal(a: Float, b: Float): Float {
            return a / b
        }
    },
    Pow {
        override fun cal(a: Float, b: Float): Float {
            return a.pow(b)
        }
    };
    abstract fun cal(a: Float, b: Float) : Float
}


fun main() {
    val myCalculator = MyCalculator()
    myCalculator
        .sum(1, 6f)
        .pow(6f)
        .sub(10)
        .multi(10)
        .sum(10)
        .divide(3)
        .reset()
        .sum(10, 34)
        .divide(5)
    println("results of calculator method: ")
    println(MyCalculator.calculator("-1 * (40 - 30 / 3 * 5 + (10 /2)) / 10  + 1 - 2 ^ 3 ^ 2"))
//    println(MyCalculator.calculator("10 + 234 * 0.1 - 0.4 * (2 ^ 5 - 2) / 30 - 30"))
    MyCalculator.live()

    //////////////////////////////////////////////////////////
    println("\n////////////////////////////////\n")
    //////////////////////////////////////////////////////////

    val myClock = MyClock(minute = 10, hour = 14)
    myClock.digitalFormat()

    myClock.set12Hour(true)
    myClock.digitalFormat()

    myClock.addOneUnit(MyClock.ClockValue.MINUTE)
    myClock.setClock(hour = 11)
    myClock.digitalFormat()

    myClock.addOneUnit(MyClock.ClockValue.HOUR)
    myClock.digitalFormat()

    println(myClock.equals(MyClock(hour = 12, minute = 11)))
    println(myClock.equals(MyClock(hour = 12, minute = 11, second = 1)))
    println(myClock.equals(MyClock(hour = 32, minute = 70)))

    myClock.setClock(23, 59, 59)
    myClock.digitalFormat()

    myClock.addOneUnit(MyClock.ClockValue.SECOND)
    myClock.digitalFormat()
}
