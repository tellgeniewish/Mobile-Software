package ddwu.com.mobile.fooddbexam

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class FoodDBHelper(context: Context?):SQLiteOpenHelper(context, DB_NAME, null, 1) /*상위 부모클래스 호출시킴*/ {
    val TAG = "FoodDBHelper"

    companion object { // 정적변수 static 선언
        const val DB_NAME = "food_db"
        const val TABLE_NAME = "food_table"
        const val COL_FOOD = "food"
        const val COL_COUNTRY = "country"
    }

    // 최초로 readable 또는 writable 사용 시 호출됨
    override fun onCreate(db: SQLiteDatabase?) { // 테이블을 만든다
        val CREATE_TABLE =
            // (괄호) 안에 컬럼들을 써준다
            "CREATE TABLE ${TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " + // AUTOINCREMENT 자동 증가하는 속성
            "${COL_FOOD} TEXT, ${COL_COUNTRY} TEXT )"
            // 안드로이드 앱에서 DB를 만들 때는 기본키 이름은 항상 _ID로 만든다
        Log.d(TAG, CREATE_TABLE)
        db?.execSQL(CREATE_TABLE) // CUD
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) { // 테이블을 업그레이드한다
        val DROP_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}" // 원래 테이블 날린다
        db?.execSQL(DROP_TABLE)
        onCreate(db) // 다시 테이블 만든다
    }
}