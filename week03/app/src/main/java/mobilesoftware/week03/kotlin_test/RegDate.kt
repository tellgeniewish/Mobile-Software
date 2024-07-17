package mobilesoftware.week03.kotlin_test

data class RegDate(val year: Int, val month: Int, val day: Int) {

    override fun equals(other: Any?): Boolean {
        return (this.year == (other as RegDate).year)
        // return (this.year == (other as RegDate).year) && (this.month == (other as RegDate).month) // A && B 의 경우 각 피연산자의 논리값(True/False) 를 확인하여 결과를 반환
        // return (this.year == (other as RegDate).year) and (this.month == (other as RegDate).month) // A and B 의 경우 각 정수 피연산자의 비트 값을 비교하여 같을 경우 True 를 반환

    }

    override fun toString(): String {
        //return super.toString() // 원래 있는 문장
        return "${year}년 ${month}월 ${day}일"
    }
}