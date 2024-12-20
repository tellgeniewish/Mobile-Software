package ddwu.com.mobile.fooddbexam02

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ddwu.com.mobile.fooddbexam02.data.FoodDBHelper
import ddwu.com.mobile.fooddbexam02.data.FoodDto
import ddwu.com.mobile.fooddbexam02.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    val addBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(addBinding.root)

        /*EditText에서 값을 읽어와 DB에 저장*/
        addBinding.btnAddFood.setOnClickListener{
            val food = addBinding.etAddFood.text.toString()
            val country = addBinding.etAddCountry.text.toString()

            if (addFood(FoodDto(0, food, country)) > 0) {
                setResult(RESULT_OK)
            } else {
                setResult(RESULT_CANCELED)
            }

            finish()
        }

        addBinding.btnAddCancel.setOnClickListener{
            setResult(RESULT_CANCELED)

            finish()
        }

    }

    fun addFood(newDto : FoodDto) : Long {// Int {
        val helper = FoodDBHelper(this)
        val db = helper.writableDatabase

        val newRow = ContentValues()
        newRow.put(FoodDBHelper.COL_FOOD, newDto.food)
        newRow.put(FoodDBHelper.COL_COUNTRY, newDto.country)

        val result = db.insert(FoodDBHelper.TABLE_NAME, null, newRow)

        helper.close()

//        return 0
        return result
    }

}