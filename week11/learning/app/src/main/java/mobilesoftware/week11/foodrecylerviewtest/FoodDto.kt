package mobilesoftware.week11.foodrecylerviewtest

// 데이터를 담아서 다른 네트워크나 계층에 전송하는 클래스를 디자인 패턴에서 DTO라 부름
data class FoodDto(val photo: Int, val food: String, var count: Int) { // 리소스 id값이 정수값이라서 photo는 Int
    override fun toString() = "$food ($count)"
//    override fun toString(): String {
//        return "$food ($count)"
//        //return super.toString()
//    }
}