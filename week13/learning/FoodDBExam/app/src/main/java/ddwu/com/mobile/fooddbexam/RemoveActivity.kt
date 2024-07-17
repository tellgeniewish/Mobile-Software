package ddwu.com.mobile.fooddbexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ddwu.com.mobile.fooddbexam.databinding.ActivityRemoveBinding

class RemoveActivity : AppCompatActivity() {

    val removeBinding by lazy {
        ActivityRemoveBinding.inflate(layoutInflater)
    }

    lateinit var helper : FoodDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(removeBinding.root)

        helper = FoodDBHelper(this)

        removeBinding.btnRemoveFood.setOnClickListener{
            deleteFood(removeBinding.etRemoveFood.text.toString())
            finish()
        }

        removeBinding.btnRemoveCancel.setOnClickListener {
            finish()
        }
    }

    fun deleteFood(food: String) {
        val db = helper.writableDatabase // 필수

        // 전용함수 사용방식 -> 권장됨
        val whereClause = "${FoodDBHelper.COL_FOOD}=?"
        //val whereArgs = arrayOf("된장찌개")
        val whereArgs = arrayOf(food)
        db.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs)

        helper.close()
    }
}