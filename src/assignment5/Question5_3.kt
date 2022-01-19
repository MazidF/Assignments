class MyClock(hour: Int? = null, minute: Int? = null, second: Int? = null) {
    var hour = 0
        private set(value) {
            if (value in 0..23) {
                field = value
            } else {
                println("Invalid value to set for hour")
            }
        }

    @JvmName("getHourInner")
    private fun getHour() = if (is12Hours) hour % 12 else hour

    var minute = 0
        private set(value) {
            if (value in 0..59) {
                field = value
            } else {
                println("Invalid value to set for minute")
            }
        }

    var second = 0
        private set(value) {
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

    constructor(myClock: MyClock) : this(myClock.hour, myClock.minute, myClock.second) {
        this.is12Hours = myClock.is12Hours
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

    override fun toString() = digitalFormat(false)

    fun digitalFormat(print: Boolean = true): String {
        var result = String.format("%02d:%02d:%02d", getHour(), minute, second)
        if (is12Hours) {
            result += (if (hour >= 12) "  PM" else "  AM")
        }
        return result.also {
            if (print) {
                println(it)
            }
        }
    }

    fun get(clockValue: ClockValue) = clockValue.get(this)

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
        // saving state of each object
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

operator fun MyClock.invoke(): Int {
    return ((hour) * 60 + minute) * 60 + second
}
// obj()

operator fun MyClock.inc() : MyClock {
    return this.apply {
        addOneUnit(MyClock.ClockValue.SECOND)
    }
}


operator fun MyClock.dec() : MyClock {
    return this - MyClock(second = 1)
}

operator fun MyClock.plus(other: MyClock) : MyClock {
    return MyClock(this).also {
        val second = (it.second + other.second)
        val minute = it.minute + other.minute + second / 60
        val hour = it.hour + other.hour + minute / 60
        it.setClock(hour % 24, minute % 60, second % 60)
    }
}

operator fun MyClock.minus(other: MyClock) : MyClock {
    return MyClock(this).also {
        val second = (it.second - other.second)
        val minute = it.minute - other.minute + if (second < 0) -1 else 0
        val hour = it.hour - other.hour + if (minute < 0) -1 else 0
        it.setClock((hour + 24) % 24, (minute + 60) % 60, (second + 60) % 60)
    }
}

fun main() {
    var clock1 = MyClock(23, 59, 59)

    println("clock1 in seconds: " + clock1()) // time in second

    val clock2 = MyClock(12, 32, 1)

    println("clock1 + clock2 : " + (clock1 + clock2))

    println("clock1 - clock2 : " + (clock1 - clock2))

    println("clock2 - clock1 : " + (clock2 - clock1))

    println("++clock1 : ${++clock1}") // 23 : 59 : 59  -->  00 : 00 : 00

    println("--clock1 : ${--clock1}") // 00 : 00 : 00  -->  23 : 59 : 59
}
