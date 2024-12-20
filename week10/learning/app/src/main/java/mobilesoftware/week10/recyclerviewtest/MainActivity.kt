package mobilesoftware.week10.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import mobilesoftware.week10.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val dao = SubjectDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        // 1. 원본 데이터 준비(배열) --> 클래스로 따로 뺐음
//        val dataList = ArrayList<String>()
//        dataList.add("모바일 소프트웨어")
//        dataList.add("웹서비스")
//        dataList.add("네트워크")
//        dataList.add("시스템 프로그래밍")
//        dataList.add("시스템/네트워크 보안")

        val dataList = dao.dataList

        val layoutManager = LinearLayoutManager(this) // 열거형
        layoutManager.orientation = LinearLayoutManager.VERTICAL // 수직
        // layoutManager.orientation = LinearLayoutManager.HORIZONTAL // 수평
        binding.recyclerView.layoutManager = layoutManager
        
        // Adapter와 결합
        val adapter = MyAdapter(this, R.layout.list_item, dataList)
        binding.recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            //val subject = binding.etSubject.text.toString()
            //dataList.add(subject)
            dataList.add(binding.etSubject.text.toString())
            adapter.notifyDataSetChanged()
        }
    }
}