import kotlin.random.Random

fun main() {
    val clazz = Class()
    for (i in 0 until 10) {
        clazz.addObserver(Student("name$i"))
    }
    clazz.cancelingTrip()
}

abstract class Observer {
    abstract fun update(message: Message)
}

abstract class Message(val message: String) {
    abstract fun action(observer: Observer)
}

class Student(private val name: String) : Observer() {
    companion object {
        var allStudent = ArrayList<Student>(10)
    }

    init {
        allStudent.add(this)
    }

    private fun doesCureAboutMessage() = Random.nextBoolean()

    override fun update(message: Message) {
        println("$name is Updated!!")
        if (doesCureAboutMessage()) {
            message.action(this)
        }
    }

    override fun toString() = name
}

class Class : Service() {
    fun cancelingTrip() {
        notifyAllObservers(object : Message("The trip has been canceled!!!") {
            override fun action(observer: Observer) {
                println("\t\t$observer read the message!!")
            }
        })
    }
}

abstract class Service {
    private val observers = ArrayList<Observer>(5)

    open fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyAllObservers(message: Message) {
        for (observer in observers) {
            observer.update(message)
        }
    }
}
