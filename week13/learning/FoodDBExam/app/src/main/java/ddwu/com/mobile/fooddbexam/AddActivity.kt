package ddwu.com.mobile.fooddbexam

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ddwu.com.mobile.fooddbexam.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    val addBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    lateinit var helper : FoodDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(addBinding.root)

        helper = FoodDBHelper(this)

        addBinding.btnAddFood.setOnClickListener{
            addFood(addBinding.etAddFood.text.toString(), addBinding.etAddCountry.text.toString())
            finish()
        }

        addBinding.btnAddCancel.setOnClickListener {
            finish()
        }
    }

    fun addFood(food: String, country: String) {
        val db = helper.writableDatabase // 필수

        // 전용함수 사용방식 -> 권장됨
        val newRow = ContentValues()
        //newRow.put(FoodDBHelper.COL_FOOD, "된장찌개")
        newRow.put(FoodDBHelper.COL_FOOD, food)
        //newRow.put(FoodDBHelper.COL_COUNTRY, "한국")
        newRow.put(FoodDBHelper.COL_COUNTRY, country)
        db.insert(FoodDBHelper.TABLE_NAME, null, newRow)

        // SQL 직접 사용방식
        db.execSQL("INSERT INTO ${FoodDBHelper.TABLE_NAME} " +
                "VALUES (NULL, '순두부찌개', '한국')")

        helper.close() // App Inspection로 확인하려면 주석처리해야 함
        // Keep Database connections Open 켜놓으면 주석처리 안 해도 ㄱㅊ
    }
}