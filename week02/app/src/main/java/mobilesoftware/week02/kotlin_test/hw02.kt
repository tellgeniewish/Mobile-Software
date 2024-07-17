package mobilesoftware.week02.kotlin_test

import java.util.Random

fun main() {
    val random = Random() // alt + Enter 누르면 import 문장 뜬다

    var lotto = mutableSetOf<Int>() // 객체가 가리키는 위치가 바뀌지 않아서 val
    // val로 선언하면 한 번 지정한 객체를 다른 객체로 변경할 수 없
    // 객체 자체의 속성값은 변경할 수 있다.

    while (lotto.size < 6) {
        var num = random.nextInt(45) + 1 // 1~45
        lotto.add(num)
    }

    print("로또 번호: $lotto ")
    /*
    for (value in lotto)
        print("$value ")
    */
}