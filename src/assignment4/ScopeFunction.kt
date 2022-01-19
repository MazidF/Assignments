class Person() {
    lateinit var name: String
    lateinit var contactNumber: String

    fun displayInfo() = print("Name: $name\n Contact Number: $contactNumber")
}

fun main1() {
    val person = Person().apply {
        name = "asdf" // why?
        contactNumber = "1234"
//        displayInfo() // i prefer this, but i have been suggested to use also()
    }.also(Person::displayInfo)
}

class Course {
    var student: Person? = Person()
    init {
        throw Exception()
    }
}

fun main2() {
    val a = Course().takeIf { true } // making course nullable
    val course: Course? = a?.apply {
        this.student?.apply {
            name = "asdf"
            contactNumber = "1234"
            displayInfo()
        }
    }
}

fun main() {
    main1()
    println("\n\n")
    main2()
}
