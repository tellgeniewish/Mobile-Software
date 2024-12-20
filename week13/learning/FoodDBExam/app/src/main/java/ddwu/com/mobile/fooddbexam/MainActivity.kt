package ddwu.com.mobile.fooddbexam

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import ddwu.com.mobile.fooddbexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var helper : FoodDBHelper

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        helper = FoodDBHelper(this) // FoodDBHelper가 호출되면서 db파일이 만들어진다
        val db = helper.readableDatabase // onCreate 호출되고 테이블이 만들어진다
        // Device Explorer > data > data > ddwu.com.mobile.fooddbexam > databases > food_db 확인 ㄱㄴ
        // 하단 App Inspection(Logcat 왼쪽) > Database Inspector > food_db > food_table 확인 ㄱㄴ
        // 리프레쉬(새로고침 아이콘) 누르기 귀찮으면 Live updates 체크!(실시간 변화 알 수 있다)
        // 가능하면 새로고침하는 것을 ㅊㅊ

        binding.btnSelect.setOnClickListener{
//            val db = helper.readableDatabase // 필수
//
//            // 전용함수 사용방식 -> 권장됨
//            val selection = "${FoodDBHelper.COL_FOOD}=?"
//            val selectionArgs = arrayOf("순두부찌개")
//            val cursor = db.query(
//                "${FoodDBHelper.TABLE_NAME}", null, selection, selectionArgs,
//                null, null, null, null
//            )
//
//            // 범위함수
//            with (cursor) {// cursor 객체 안에 객체가 없으면 자동으로 cursor
//                while (moveToNext()) {
//                    val id = getInt(getColumnIndex(BaseColumns._ID))
//                    val food = getString(getColumnIndex(FoodDBHelper.COL_FOOD))
//                    val country = getString(getColumnIndex(FoodDBHelper.COL_COUNTRY))
//                    Log.d(TAG, "$id - $food ($country)")
//                }
//            }
//
//            cursor.close()
//            helper.close()

            //showFoods()
            val foodList = showFoods()

            var data = ""
            for (food in foodList) {
                data += food.toString() + "\n"
            }
            binding.tvDisplay.text = data
        }

        binding.btnAdd.setOnClickListener{
//            val db = helper.writableDatabase // 필수
//
//            // 전용함수 사용방식 -> 권장됨
//            val newRow = ContentValues()
//            newRow.put(FoodDBHelper.COL_FOOD, "된장찌개")
//            newRow.put(FoodDBHelper.COL_COUNTRY, "한국")
//            db.insert(FoodDBHelper.TABLE_NAME, null, newRow)
//
//            // SQL 직접 사용방식
//            db.execSQL("INSERT INTO ${FoodDBHelper.TABLE_NAME} " +
//                        "VALUES (NULL, '순두부찌개', '한국')")
//
//            helper.close() // App Inspection로 확인하려면 주석처리해야 함
//            // Keep Database connections Open 켜놓으면 주석처리 안 해도 ㄱㅊ

            //addFood()
            //addFood("된장찌개", "한국")

            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener{
//            val db = helper.writableDatabase // 필수
//
//            // 전용함수 사용방식 -> 권장됨
//            val updateRow = ContentValues()
//            updateRow.put(FoodDBHelper.COL_COUNTRY, "코리아")
//            val whereClause = "${FoodDBHelper.COL_FOOD}=?"
//            val whereArgs = arrayOf("된장찌개")
//            db.update(FoodDBHelper.TABLE_NAME, updateRow, whereClause, whereArgs)
//
//            helper.close()

            //modifyFood()

            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        binding.btnRemove.setOnClickListener{
//            val db = helper.writableDatabase // 필수
//
//            // 전용함수 사용방식 -> 권장됨
//            val whereClause = "${FoodDBHelper.COL_FOOD}=?"
//            val whereArgs = arrayOf("된장찌개")
//            db.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs)
//
//            helper.close()

            //deleteFood()

            val intent = Intent(this, RemoveActivity::class.java)
            startActivity(intent)
        }

    }

    /*
    //fun addFood() {
    fun addFood(food: String, country: String) {
        val db = helper.writableDatabase // 필수

        // 전용함수 사용방식 -> 권장됨
        val newRow = ContentValues()
        newRow.put(FoodDBHelper.COL_FOOD, "된장찌개")
        newRow.put(FoodDBHelper.COL_COUNTRY, "한국")
        db.insert(FoodDBHelper.TABLE_NAME, null, newRow)

        // SQL 직접 사용방식
        db.execSQL("INSERT INTO ${FoodDBHelper.TABLE_NAME} " +
                "VALUES (NULL, '순두부찌개', '한국')")

        helper.close() // App Inspection로 확인하려면 주석처리해야 함
        // Keep Database connections Open 켜놓으면 주석처리 안 해도 ㄱㅊ
    }
    */

    /*
    fun modifyFood() {
        val db = helper.writableDatabase // 필수

        // 전용함수 사용방식 -> 권장됨
        val updateRow = ContentValues()
        updateRow.put(FoodDBHelper.COL_COUNTRY, "코리아")
        val whereClause = "${FoodDBHelper.COL_FOOD}=?"
        val whereArgs = arrayOf("된장찌개")
        db.update(FoodDBHelper.TABLE_NAME, updateRow, whereClause, whereArgs)

        helper.close()
    }
    */

    /*
    fun deleteFood() {
        val db = helper.writableDatabase // 필수

        // 전용함수 사용방식 -> 권장됨
        val whereClause = "${FoodDBHelper.COL_FOOD}=?"
        val whereArgs = arrayOf("된장찌개")
        db.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs)

        helper.close()
    }
    */

    @SuppressLint("Range")
    //fun showFoods() {
    fun showFoods() : List<FoodDto>{
        val db = helper.readableDatabase // 필수

        // 전용함수 사용방식 -> 권장됨
        val selection = "${FoodDBHelper.COL_FOOD}=?"
        val selectionArgs = arrayOf("순두부찌개")
        val cursor = db.query(
            //"${FoodDBHelper.TABLE_NAME}", null, selection, selectionArgs,
            "${FoodDBHelper.TABLE_NAME}", null, null, null,
            null, null, null, null
        )

        val foodList = arrayListOf<FoodDto>()

        // 범위함수
        with (cursor) {// cursor 객체 안에 객체가 없으면 자동으로 cursor
            while (moveToNext()) {
                val id = getInt(getColumnIndex(BaseColumns._ID))
                val food = getString(getColumnIndex(FoodDBHelper.COL_FOOD))
                val country = getString(getColumnIndex(FoodDBHelper.COL_COUNTRY))
                Log.d(TAG, "$id - $food ($country)")

                val dto = FoodDto(id, food, country)
                foodList.add(dto)
            }
        }

        cursor.close()
        helper.close()

        return foodList
    }

}