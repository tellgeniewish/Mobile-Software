package ddwu.com.mobile.foodexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.foodexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val Add_Food_ACTIVITY_CODE = 100

    val foods = FoodDao().foods
    val adapter = FoodAdapter(foods)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 데이터 준비
        val foods = FoodDao().foods

        /* 여기부터 */
        // 레이아웃매니져 생성 및 설정
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // 어댑터 생성
        //val adapter = FoodAdapter(foods)

        // Recylerview에 레이아웃매니져 및 어댑터 설정
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        /* 여기까지는 onCreate에서 수행하는 코드!
        액티비티가 파괴되는 경우 다시 onCreate 이 실행됨 */

        // btnAdd를 클릭하면 AddFoodActivity 실행
        binding.btnAdd.setOnClickListener{
            val intent = Intent(this/*MainActivity가 Context를 상속받았기에 사용 ㄱㄴ*/, AddFoodActivity::class.java) // 명시적
            startActivityForResult(intent, Add_Food_ACTIVITY_CODE)// 결과가 올 것을 기대하고 띄움
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Add_Food_ACTIVITY_CODE) {
            when(resultCode) {
                RESULT_OK -> {
                    val result = data?.getSerializableExtra("result_data") as FoodDto // 타입 캐스팅하면 FoodDto 타입이 됨
                    Log.d(TAG, result.toString()!!)

                    foods.add(result)
                    adapter.notifyDataSetChanged()
                }
                RESULT_CANCELED -> {
                    Log.d(TAG, "Canceled")
                }
            }
        }
    }
}