package ddwu.com.mobile.fooddbexam

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import ddwu.com.mobile.fooddbexam.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    val updateBinding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    lateinit var helper : FoodDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(updateBinding.root)

        helper = FoodDBHelper(this)

        updateBinding.btnUpdateFood.setOnClickListener{
            modifyFood(updateBinding.etUpdateId.text.toString(), updateBinding.etUpdateFood.text.toString())
            finish()
        }
        updateBinding.btnUpdateCancel.setOnClickListener {
            finish()
        }
    }

    fun modifyFood(id: String, food: String) {
        val db = helper.writableDatabase // 필수

        // 전용함수 사용방식 -> 권장됨
        val updateRow = ContentValues()
        //updateRow.put(FoodDBHelper.COL_COUNTRY, "한국")
        updateRow.put(FoodDBHelper.COL_FOOD, food)
        //val whereClause = "${FoodDBHelper.COL_FOOD}=?"
        val whereClause = "${BaseColumns._ID}=?"
        //val whereArgs = arrayOf("된장찌개")
        val whereArgs = arrayOf(id)
        db.update(FoodDBHelper.TABLE_NAME, updateRow, whereClause, whereArgs)

        helper.close()
    }
}