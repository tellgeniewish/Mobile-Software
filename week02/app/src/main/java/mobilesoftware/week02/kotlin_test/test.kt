package mobilesoftware.week02.kotlin_test

// 전역 변수는 반드시 초기화해야 함
val myValue : Int = 10 // val은 변하지 않는 값
var myVariable = 5 // var은 바꾸기 ㄱㄴ

// 프로그램 실행 중인 런타임 중 입력받고 싶을 때
val data_value : Int by lazy {
    println("val init")
    1234 // 마지막 줄에 초기화값
}
lateinit var data_variable : String

var data01 : Int = 10
var data02 : Int? = null // ? 쓰면 null 대입 ㄱㄴ
var data03 : Any = "20"

val myArray : Array<Int> = Array(3, {0}) // 사이즈, 초기값
val intArray02 = IntArray(3, {0})
val intArray03 = arrayOf<Int>(1, 2, 3)
val intArray04 = intArrayOf(4, 5, 6)

val myList : List<Int> = List(3, {0})
val myList02 = listOf(1,2,3)
val MuList : MutableList<Int>  = MutableList(3, {0})
val MuList02 = mutableListOf<Int>(1, 2, 3)

fun main() { // 반환 값이 없으면 Unit 또는 생략
    println("Hello World!!!")

    //myValue = 5 // error
    // 지역 변수 선언 시 초기화 안 해도 허용
    myVariable = 10
    val newValue : Int

    data_variable = "test"
    println("val: $data_value var: $data_variable")

    var data04 = data01.toFloat() // data01이 객체라서 함수 사용 ㄱㄴ
    println("$data01, ${data02}, $data03 $data04")

    println("${default_value()} ${default_value(10)}")
    find_seat(1, 2)
    find_seat(value2=20, value1=10)
    //println("${find_seat(1, 2)} ${find_seat(value2=20, value1=10)}")

    println("${myArray[0]}, ${myArray.get(0)}")
    myArray[1] =10
    myArray.set(2, 20)
    println("${myArray[1]}, ${myArray.get(2)}")

    println("array size: ${myArray.size}")

    //myList[0] = 10 // error! Mutable이 아니라서 변경 불가
    MuList[0] = 10
    MuList.add(4) // 추가
    MuList02.set(0, 10) // 기존 항목 변경
    println("${MuList} ${MuList02}")

    val value = 10
    if (10 < value) {
        println("Up")
    } else {
        println("Down")
    }

    val result : Boolean = if (5 < value) {
        true
    } else {
        false
    }
    println("result: $result")

    val num : Any = 10
    val num_bool = when (num) {
        is Int -> { // is 자료형 확인
            println("Integer!!!")
            true
        }
        is String -> false
        10 -> true
        in 5..20 -> true // in a..b a이상 b이하
        // a..<b 는 a이상 b미만
        else -> false
    }
    println("num_bool: $num_bool")

    while (true) {
        print("Input a number(0 for exit): ")
        val input_data = readLine()?.toInt() // 입력은 모두 string
        // null이 입력될 수도 있어서 ?

        if (input_data != 0)
            continue
        else
            break
    }

    for (i in 1..10) // i는 지역변수! 자료형 안 쓴다!
        print("$i ")
    println()

    for (i in 1 until 10) // until은 a이상 b미만 = a..<b
        print("$i ")
    println()

    for (i in 1..10 step 3)
        print("$i ")
    println()

    for (i in 10 downTo 2) // a부터 b까지 감소
        print("$i ")
    println()

    for (i in 10 downTo 2 step 3)
        print("$i ")
    println()

    val num_data = intArrayOf(1, 2, 3, 4, 5)

    for (a in num_data)
        print("$a ")
    println()

    for (index in num_data.indices) {
        println("index: $index value: ${num_data[index]}")
    }

    for ((index, a) in num_data.withIndex()) {
        println("[$index] : $a")
    }

    // kotlin에서 입력받는 방법: readline()
    val kotlin_input = readLine()!!.toInt() // !!은 null 들어올 수 없다! 무조건 값 입력

    // 키보드 입력값으로 배열 초기화하기
    val input_array = Array<Int>(3) { readLine()!!.toInt() }
}

fun no_return_func() : Unit {
println("no return")
}

fun null_retrun_func() : Nothing? { // null 반환 시 nohting 뒤에 ? 붙여야 함
return null
}

fun exception_return_func() : Nothing {
throw Exception()
}

fun square(value : Int) : Int { // 함수 기본 형태
    return value * value
}

fun no_return(value : Int) : Unit {
    //value = 10 // error 매개변수 값 변경 금지! 전달받은 값 그대로 사용
}

fun default_value(value : Int = 1) : Int { // 매개변수 기본 값 1로 고정
    return value * value
}

fun find_seat(value1: Int, value2: Int) : Unit { // 매개변수
    println("add == ${value1 + value2}")
}