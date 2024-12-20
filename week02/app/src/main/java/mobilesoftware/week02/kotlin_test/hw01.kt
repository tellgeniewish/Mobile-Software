package mobilesoftware.week02.kotlin_test

import kotlin.math.pow

fun main() {
    print("키를 입력하시오.(cm 단위): ")
    val cm = readLine()!!.toFloat()
    print("몸무게를 입력하시오.(kg 단위): ")
    val kg = readLine()!!.toFloat()

    //val BMI = kg / (cm/100.0)*(cm/100.0)
    val BMI = kg / (cm/100.0).pow(2) // 제곱 함수

    /*
    var state = "저체중"
    if (25 < BMI)
        state = "비만"
    else if (23 < BMI)
        state = "과체충"
    else if (18.5 < BMI)
        state = "정상"
     */
    val state = when (BMI) {
        in 0.0 .. 18.5 -> "저체중"
        in 18.5 .. 23.0 -> "정상"
        in 23.0 .. 25.0 -> "과체충"
        else -> "비만"

    }

    println("키: $cm cm, 몸무게: ${kg}kg의 BMI 지수는 $BMI 이며 $state 입니다.")
}