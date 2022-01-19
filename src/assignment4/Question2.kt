import java.lang.Exception
import java.text.DateFormatSymbols
import java.time.Month

class Me {
    companion object {
        val months = DateFormatSymbols.getInstance().months.toList().subList(0, 12)
        val monthsNumber = Month.values().map { month -> month.length(true) }
        fun getIntFromUser() {
            println("Enter 'exit' to end this method :)")
            var index: Int
            while (true) {
                try {
                    index = (readLine()!!.run {
                        if (this == "exit") null else this
                    }?.toInt() ?: break) - 1

                    println("${months[index]} -> ${monthsNumber[index]}")
                } catch (e: Exception) {
                    println("Wrong Input")
                }
            }
        }
    }
}

fun main() {
    Me.getIntFromUser()
}
