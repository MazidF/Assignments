import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

const val exit = "For input string: \"exit\""

fun main() {
    val scanner = Scanner(System.`in`)
    var i = 2
    var lastArr: Array<Any>
    var arr: Array<Any>
    var a: Int = 0
    var arrIndex: Int
    while (true) {
        try {
            a = scanner.next().run {
                if (this == "exit") {
                    null
                } else {
                    this
                }
            }!!.toInt()
            break
        } catch (e: NullPointerException) {
            println(0)
            exitProcess(0)
        } catch (e: NumberFormatException) {
            println("Invalid input!!\ttry again")
        }
    }
    lastArr = arrayOf(0)
    var list: ArrayList<Int>
    while (true) {
        arr = Array(i) { 0 }
        arrIndex = 0
        try {
            a = scanner.next().toInt()
            arr[arrIndex++] = lastArr[0] as Int + a
            for (j in 1 until i - 1) {
                a = scanner.next().toInt()
                list = ArrayList()

                lastArr[j - 1].run {
                    if (this is Int) {
                        list.add(this + a)
                    } else if (this is ArrayList<*>) {
                        for (item in this) {
                            list.add(item as Int + a)
                        }
                    }
                    Unit
                }

                lastArr[j].run {
                    if (this is Int) {
                        list.add(this + a)
                    } else if (this is ArrayList<*>) {
                        for (item in this) {
                            list.add(item as Int + a)
                        }
                    }
                    Unit
                }

                arr[arrIndex++] = list
            }
            a = scanner.next().toInt()
            arr[arrIndex] = lastArr[i - 2] as Int + a
            i++
            lastArr = arr
            println(lastArr.contentToString())
        } catch (e: NumberFormatException) {
            if (e.message == exit) {
                break
            } else {
                println("Invalid input!!\t try again")
                scanner.nextLine()
            }
        }
    }
    println(lastArr.map {
        if (it is Int) {
            it
        } else {
            (it as ArrayList<Int>).reduce(::max)
        }
    }.reduce(::max))
}
fun max(it: Int, other: Int) = if (it > other) it else other
