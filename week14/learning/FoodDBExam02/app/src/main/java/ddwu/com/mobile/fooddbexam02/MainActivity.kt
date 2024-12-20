package ddwu.com.mobile.fooddbexam02

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.fooddbexam02.data.FoodDBHelper
import ddwu.com.mobile.fooddbexam02.data.FoodDao
import ddwu.com.mobile.fooddbexam02.data.FoodDto
import ddwu.com.mobile.fooddbexam02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val REQ_ADD = 100
    val REQ_UPDATE = 200

    val dao: FoodDao by lazy {
        FoodDao(this)
    }

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var foods : ArrayList<FoodDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*RecyclerView 의 layoutManager 지정*/
        binding.rvFoods.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        foods = dao.getAllFoods()//getAllFoods()              // DB 에서 모든 food를 가져옴 // ctrl 누르고 클릭하면 해당 함수로 이동함!
        val adapter = FoodAdapter(foods)        // adapter 에 데이터 설정
        binding.rvFoods.adapter = adapter   // RecylcerView 에 adapter 설정

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivityForResult(intent, REQ_ADD)
        }

        adapter.setOnItemClickListener(object : FoodAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("dto", foods.get(position) )   // 클릭한 RecyclerView 항목 위치의 dto 전달
                startActivityForResult(intent, REQ_UPDATE)      // 수정결과를 받아오도록 Activity 호출
            }
        })

        adapter.setOnItemLongClickListener(object : FoodAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int) { // position은 RecyclerView에서 몇 번째로 보이는지 --> _ID와는 다르다
                Toast.makeText(this@MainActivity, "롱클릭! ${foods.get(position)}", Toast.LENGTH_SHORT).show()

                if (deleteFood(foods.get(position)) > 0) {
                    foods.clear()
                    foods.addAll(dao.getAllFoods())//getAllFoods())

                    binding.rvFoods.adapter?.notifyDataSetChanged() // 주석 처리할 시 원본 데이터랑 화면이 달라서 죽음
                }
            }
        }) // 고차함수 형태로 어떻게 구현할지 고민해보자
    }

    override fun onResume() {
        super.onResume()
//        adapter.notifyDataSetChanged()   // 액티비티가 보일 때마다 RecyclerView 를 갱신하고자 할 경우
        binding.rvFoods.adapter?.notifyDataSetChanged()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_UPDATE -> {
                if (resultCode == RESULT_OK) {
                    foods.clear()                       // 기존 항목 제거 // List 내용을 없앰 // 얘를 주석처리 해보고 실행하면 기존 List에 추가됨
                    foods.addAll(dao.getAllFoods())         // 항목 추가 // DB를 다시 읽어서 추가
//                    adapter.notifyDataSetChanged()      // RecyclerView 갱신
//                    binding.rvFoods.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "취소됨", Toast.LENGTH_SHORT).show()
                }
            }
            REQ_ADD -> {
                if (resultCode == RESULT_OK) {
                    foods.clear()
                    foods.addAll(dao.getAllFoods())
                } else {
                    Toast.makeText(this@MainActivity, "add 취소됨", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


//    @SuppressLint("Range")
//    fun getAllFoods() : ArrayList<FoodDto> {
//        val helper = FoodDBHelper(this)
//        val db = helper.readableDatabase
////        val cursor = db.rawQuery("SELECT * FROM ${FoodDBHelper.TABLE_NAME}", null)
//        val cursor = db.query(FoodDBHelper.TABLE_NAME, null, null, null, null, null, null)
//
//        val foods = arrayListOf<FoodDto>()
//        with (cursor) {
//            while (moveToNext()) {
//                val id = getInt( getColumnIndex(BaseColumns._ID) )
//                val food = getString ( getColumnIndex(FoodDBHelper.COL_FOOD) )
//                val country = getString ( getColumnIndex(FoodDBHelper.COL_COUNTRY) )
//                val dto = FoodDto(id, food, country)
//                foods.add(dto)
//            }
//        }
//        return foods
//    }

    fun deleteFood(dto: FoodDto): Int {
        val helper = FoodDBHelper(this)
        val db = helper.writableDatabase

        val whereClause = "${BaseColumns._ID}=?" // DB에서 삭제할 때는 _ID로 삭제해야 함
        val whereArgs = arrayOf(dto.id.toString()) // 문자열이어야 함
        val result = db.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs)

        helper.close()

        //return 0
        return result
    }

}