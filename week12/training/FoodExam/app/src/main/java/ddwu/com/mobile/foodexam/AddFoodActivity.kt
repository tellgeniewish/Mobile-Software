package ddwu.com.mobile.foodexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ddwu.com.mobile.foodexam.databinding.ActivityAddFoodBinding

class AddFoodActivity : AppCompatActivity() {
    val TAG = "AddFoodActivity"
    val binding by lazy {
        ActivityAddFoodBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            val new_food = FoodDto(binding.etNewFood.text.toString(), binding.etCountry.text.toString())

            val resultIntent = Intent() // 완전히 비어있는 인텐트를 만든다 --> 돌아갈 항목이 안 정해져 있음
            resultIntent.putExtra("result_data", new_food)

            setResult(RESULT_OK, resultIntent) // 결과가 잘 만들어졌다는 신호를 주거나 / 결과를 전달
            finish() // 이 액티비티가 종료될 때 결과를 돌려줌
        }
        binding.btnCancel.setOnClickListener{
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}