package mobilesoftware.week10.adapterviewtest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import mobilesoftware.week10.adapterviewtest01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val dao = CatDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dataList = dao.dataList

        val layoutManager = LinearLayoutManager(this) // 열거형
        layoutManager.orientation = LinearLayoutManager.VERTICAL // 수직
        binding.recyclerView.layoutManager = layoutManager

        // Adapter와 결합
        val adapter = MyAdapter(this, R.layout.list_item, dataList)
        binding.recyclerView.adapter = adapter
    }
}