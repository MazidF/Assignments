package KotlinExam

import java.util.regex.Pattern

fun main() {
    println(function3("  fly me   to   the moon  "))
    println(function3Second("  fly me   to   the moon  "))
}

val pattern = Pattern.compile("([a-zA-Z])+$")

fun function3(s: String): String {
    val matcher = pattern.matcher(s.trim())
    if (matcher.find()) {
        return matcher.group()
    }
    return "null"
}

fun function3Second(s: String): String {
    return s.trim().split(" ").last {
        it.isNotEmpty()
    }
}
