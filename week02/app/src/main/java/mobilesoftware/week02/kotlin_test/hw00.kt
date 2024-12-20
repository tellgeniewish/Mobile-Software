package mobilesoftware.week02.kotlin_test

fun main() {
    println("숫자 입력:")
    val num_array = Array<Int>(5) { readLine()!!.toInt() }

    var total = num_array.sum()
    /*
    var total : Int = 0
    for (num in num_array) {
        total += num
    }
    */

    /*
    for (i in 1..5) {
        val value = readLine()!!.toInt()
        total += value
    }
    */
    println("sum: $total avg: ${total.toFloat()/num_array.size}")
}