import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

class Branch(val code: String, val city: String, rate: Int) {
    var rate: Int = 0
        set(value) {
            field = if (value in 1..3) {
                value
            } else {
                println(value)
                throw Exception("Invalid rate")
            }
        }

    init {
        this.rate = rate
    }

    override fun toString(): String {
        return "{Branch[$code] -> city=$city, rate=$rate}"
    }
}

class Account(val accountNumber: String, var balance: Double, val branch: Branch) {
    override fun toString(): String {
        return "{Account[$accountNumber] -> balance=$balance, $branch}"
    }
}

class CreditCard(val cardNumber: String, var credit: Double) {
    override fun toString(): String {
        return "{CreditCard[$cardNumber] -> credit=$credit}"
    }
}

class Customer(val name: String, val account: Account, val creditCard: CreditCard) {
    fun calCustomerBalance(): Double {
        return creditCard.credit + account.balance
    }
    override fun toString(): String {
        return "$name ->\t\t$account,\t\t$creditCard"
    }
}

fun main() {
    val branches = Array(5) {
        Branch("branch$it", "city$it", Random.nextInt(1..3))
    }

    val customers = ArrayList<Customer>(20)
    var account: Account
    for (i in 0 until 20) {
        account = Account("account$i", (i + 2.0) * i, branches[Random.nextInt(0..4)])
        customers.add(Customer("customer$i", account, CreditCard("11111$i", i * 10.0)))
    }
    for (customer in customers) {
        println(customer)
    }
    println(branches.toList())

    var sum = 0.0
    for (customer in customers) {
        sum += customer.calCustomerBalance()
    }
    println("sum:\t\t${sum.toInt()}") // برای این که از 'E' جلوگیری کنم، به int تبدیل کردم

    val sums = Array(5) { 0.0 }
    for (customer in customers) {
        sums[customer.account.branch.code.substring(6).toInt()] += customer.calCustomerBalance()
    }
    for (s in sums.indices) {
        println("branch$s:\t${sums[s].toInt()}")
    }
}
