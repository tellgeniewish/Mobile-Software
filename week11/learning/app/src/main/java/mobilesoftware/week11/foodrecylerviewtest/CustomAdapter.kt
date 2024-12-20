package mobilesoftware.week11.foodrecylerviewtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobilesoftware.week11.foodrecylerviewtest.databinding.ListItemBinding
import mobilesoftware.week11.foodrecylerviewtest.databinding.ListWeatherBinding

class CustomAdapter(val weathers: ArrayList<WeatherDto>): RecyclerView.Adapter<CustomAdapter.WeatherViewHolder>() {
    override fun getItemCount(): Int = weathers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val weatherBinding = ListWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(weatherBinding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.weatherBinding.tvNo.text = weathers[position].no
        holder.weatherBinding.tvDong.text = weathers[position].dong
        holder.weatherBinding.tvLoc.text = weathers[position].loc
        holder.weatherBinding.tvWeather.text = weathers[position].weather
    }

    inner class WeatherViewHolder(/*view: View*/ val weatherBinding: ListWeatherBinding): RecyclerView.ViewHolder(weatherBinding.root) {
    }
}