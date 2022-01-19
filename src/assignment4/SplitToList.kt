fun main() {
    "A big black bear sat on a big black rug".run {
        println(myList())
        println(myList2())
    }
}

fun String.myList(str: String = "***"): List<String> {
    val result = mutableListOf<String>()
    val list = split(" ")
    for (s in list) {
        s.run {
            if (length == 3) {
                result.add(str)
            }
            result.add(this)
        }
    }
    return result
}

fun String.myList2(str: String = "***"): List<String> {
    return split(" ").map {
        if (it.length == 3) {
            arrayOf("***", it)
        } else {
            arrayOf(it)
        }
    }.reduce { a, b ->
        a + b
    }.toList()
}
