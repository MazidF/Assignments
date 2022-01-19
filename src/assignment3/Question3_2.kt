fun main() {
    functionName(readLine()!!)
}

fun functionName(input: String, string: String? = null) {
    val result = findAllCombination(List(input.length) {input[it] + ""})

    //////////////// using HashMap //////////////////////
    val map = HashMap<String, List<String>>()
    map[input] = result
    //////////////// using HashMap //////////////////////

    if (string == null) {
        println(result)
    } else {
        println(if ((map[input] ?: throw Exception("Error!!!!")).contains(string)) "Pass" else "Fail")
    }
}

tailrec fun findAllCombination(list: List<String>) : List<String> {
    if (list.size == 1) return list
    val result = ArrayList<String>()
    for (i in list.indices) {
        val s = list[i]
        val recursiveResult = findAllCombination(list.subList(0, i) + list.subList(i+1, list.size))
        val array = Array(recursiveResult.size) {
            s + recursiveResult[it]
        }
        result.addAll(array)
    }
    return result.toList()
}
