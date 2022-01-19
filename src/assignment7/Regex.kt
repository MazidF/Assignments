import java.util.*
import java.util.regex.Pattern

fun main() {
    val string = readLine()!!
    println(checker(string))
}

fun checker(string: String): Boolean {
    if (string.length % 2 == 1) return false
    val stack = Stack<Char>()
    var result = true
    var c: Char?
    fun check(char: Char) {
        c = stack.pop()
        result = (c != null && c == char)
    }
    for (i in string) {
        when(i) {
            '(', '[', '{' -> stack.push(i)
            ')' -> check('(')
            ']' -> check('[')
            '}' -> check('{')
        }
        if (!result) return false
    }
    return stack.isEmpty()
}
