package mobilesoftware.week12.activitytest

import java.io.Serializable

// 인터페이스임
data class FoodDto(val photo: Int, val food: String, var count: Int) : Serializable
