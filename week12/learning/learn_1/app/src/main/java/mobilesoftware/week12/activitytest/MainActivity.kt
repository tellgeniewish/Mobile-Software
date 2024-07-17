package mobilesoftware.week12.activitytest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mobilesoftware.week12.activitytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // Activity()를 상속받고 있지만 호환성을 위해 확장된 AppCompatActivity()을 상속받음
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val DETAIL_ACTIVITY_CODE = 100
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val intent = Intent(this/*MainActivity가 Context를 상속받았기에 사용 ㄱㄴ*/, DetailActivity::class.java) // 명시적
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1234-5678")) // 묵시적(특정 앱 지정X. 전화 기능이 있는 앱 중 하나를 띄움)
//            intent.putExtra("subject", "Mobile Software")

//            val dto = FoodDto(R.mipmap.ic_launcher, "치킨", 10)
//            intent.putExtra("food", dto) // 에러 이유: FoodDto는 우리가 만든 형식이라 없음 --> Serializable을 상속받으면 됨

            //startActivity(intent) // 오류가 나면 Logcat 상단 문장과 아래 문장 중 내 클래스 이름의 줄 보기
            // 오류 나는 이유: manifest에 SubActivity 등록 안 되어 있음

            startActivityForResult(intent, DETAIL_ACTIVITY_CODE)// 결과가 올 것을 기대하고 띄움
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { // requestCode == DETAIL_ACTIVITY_CODE, resultCode == RESULT_OK
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DETAIL_ACTIVITY_CODE) {
            when(resultCode) {
                RESULT_OK -> {
                    val result = data?.getStringExtra("result_data")
                    Log.d(TAG, result!!)
                }
                RESULT_CANCELED -> {
                    Log.d(TAG, "Canceled")
                }
            }
        }
    }
}