import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList
import kotlin.math.E
import kotlin.system.exitProcess

class MyException(msg: String): Exception(msg)

data class Employee(var firstName: String, var lastName: String, var salary: Long = -1L, var raise: Int) {


    companion object {
        var employees = ArrayList<Employee>()
        fun add(employee: Employee) {
            employees.add(employee)
//            println(employee.toString())
        }
    }

    init {
        checkName(firstName, "firstName")
        checkName(lastName, "lastName")
        require(salary > 0) {
            throw MyException("salary value is not proper!!")
        }
        add(this)
    }

    fun getName() = "$firstName $lastName"
    fun getAnnualSalary() = salary * 12
    fun raiseSalary(percent: Int): Long {
        salary += salary * percent / 100
        return salary
    }
}

fun checkName(value: String, msg: String): Boolean {
    if (value.trim().isEmpty()) throw MyException("invalid $msg !!!")
    return true
}

fun main() {
    val scanner = Scanner(System.`in`)
    var firstName: String
    println("Enter something like this: ==> ' name family salary percent '")
    while (true) {
        try {
            firstName = scanner.next()
            if (firstName == "exit") {
                break
            }
            Employee(firstName, scanner.next(), scanner.nextLong(),scanner.nextInt())
        } catch (e: Exception) {
            println(
            if (e is MyException) {
                e.message
            } else{
                "input has a problem"
            })
            println("try again")
            scanner.nextLine() // ignoring this
        }
    }
    println("/////////////outputs///////////////////")
    for (employee in Employee.employees) {
        with(employee) {
            println(getName())
            println(getAnnualSalary())
            println(raiseSalary(raise))
            println(getAnnualSalary())
        }
        println("////////////////////////////")
    }
}

