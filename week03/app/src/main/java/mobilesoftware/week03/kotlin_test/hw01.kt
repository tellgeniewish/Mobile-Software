package mobilesoftware.week03.kotlin_test

fun main() {
    val reg01 = RegDate(2024, 3, 1)
    val reg02 = RegDate(2024, 3, 15)

    println(reg01)
    println(reg02)
    println(reg01.equals(reg02)) // data 안 붙이면 false 뜬다
}