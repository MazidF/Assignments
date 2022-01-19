package KotlinExam

fun main() {
    function2("username=test&action=login&action=goToPage&action=login")
    function2("user=javad&user=ali&user=javad&password=1234&password=4321&option=true")
    "".trim{
        it >= 'a'
    }
}

typealias MyMap = HashMap<String, Any>

fun function2(query: String) {
    val map = HashMap<String, ArrayList<String>>()
    val orderList = ArrayList<String>()
    val list = query.split("&")
    list.map {
        it.split("=")
    }.forEach {
        if (!orderList.contains(it[0])) {
            orderList.add(it[0])
        }

        val l = map.getOrDefault(it[0], ArrayList())
        l.addAll(it.subList(1, it.size).filter {
            !l.contains(it)
        })
        map[it[0]] = l
    }
    print("{")
    for (k in orderList) {
        val v = map[k]!!
        print("'$k'=")
        if (v.size == 1) {
            print("'${v[0]}'")
        } else {
            print("[")
            val list = v.map {
                "'$it'"
            }.toList()
            print(list.joinToString(", "))
            print("]")
        }
        if (k != orderList.last()) {
            print(", ")
        }
    }
    println("}")
}
