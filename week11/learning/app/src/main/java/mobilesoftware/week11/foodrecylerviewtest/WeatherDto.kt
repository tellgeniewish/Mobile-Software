package mobilesoftware.week11.foodrecylerviewtest

class WeatherDto(val no: String, val dong: String, val loc: String, val weather: String) {
    override fun toString(): String {
        return "$no ($dong) $loc $weather"
    }
}