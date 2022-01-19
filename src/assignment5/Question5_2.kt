fun main() {
    val elements: Array<A> = arrayOf(D(), A(), C(), B())
    for (i in elements.indices) {
        println(elements[i].method1())
        println(elements[i].method2())
        println()
    }
}
open class A {
    open fun method1(): String {
        return this::class.java.name + 1
    }
    fun method2(): String {
        val method1 = method1()
        if (this !is B) {
            return "A2"
        }
        return  method1 + if (method1 == this::class.java.name + 1) {
            this::class.java.superclass.name + 2
        } else {
            this::class.java.name + 2
        }
    }

}

open class B : A() {
    override fun method1(): String {
        return if (this is C || this is D) {
            super.method1()
        } else {
            this::class.java.superclass.name + 1 // A1
        }
    }
}


class C : B()


class D : B()
