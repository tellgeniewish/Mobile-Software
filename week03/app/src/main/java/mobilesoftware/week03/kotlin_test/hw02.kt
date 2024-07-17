package mobilesoftware.week03.kotlin_test

class Member(var name: String, var RegDate: RegDate, var Address: String) {
    fun getShippingCost():Int {
        if (Address == "Seoul") // seoul 은 3000
            return 3000
        else // 그 외의 지역은 4000 반환
            return 4000
    }
    fun getTerm(year : Int, month : Int): String {
        var m = month - RegDate.month
        var y = (year - RegDate.year)*12

        if (24 <= y+m) // 가입한지 24개월이 넘을 경우 longterm
            return "longterm"
        else // 아닐 경우 short-term 반환
            return "short-term"
    }

    var getPeriod: ()-> Int = {
        val currentDate = RegDate(2024, 4, 7) // 현재 날짜를 임의로 설정
        val currentDays = currentDate.year * 365 + currentDate.month * 30 + currentDate.day

        var month = when(RegDate.month) {
            1, 3, 5, 7, 8, 10, 12 -> 30
            4, 6, 9, 11 -> 31
            else -> 29
        }
        var regDays = RegDate.year * 365 + month * 30 + RegDate.day

        currentDays - regDays
    }
}

fun main() {
    val reg = RegDate(2024, 3, 15)
    val myMem = Member("GN", reg,"Seoul")

    println("Name: ${myMem.name}")
    println("Join Date: ${myMem.RegDate}")
    println("Address: ${myMem.Address}")

    println(myMem.getShippingCost())
    println(myMem.getTerm(2022, 4))
    println(myMem.getPeriod())
}