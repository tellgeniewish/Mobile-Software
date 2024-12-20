package mobilesoftware.week12.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mobilesoftware.week12.activitytest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    val detailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    val TAG = "DetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(detailBinding.root)
        
        // 코틀린의 경우 intent 바로 사용 ㄱㄴ. intent는 멤버변수
//        val subject = intent.getStringExtra("subject") // 빈 문자열이나 null이 들어갈 수도 있어서 체크해야 할 수도
//        Log.d(TAG, subject!!)
        
//        val subject = intent.getSerializableExtra("food") // 타입 캐스팅 안 하면 Serializable 타입이 됨
//        val subject = intent.getSerializableExtra("food") as FoodDto // 타입 캐스팅하면 FoodDto 타입이 됨
//        Log.d(TAG, subject.toString())
        
        detailBinding.btnResult.setOnClickListener{
            val resultIntent = Intent() // 완전히 비어있는 인텐트를 만든다 --> 돌아갈 항목이 안 정해져 있음
            resultIntent.putExtra("result_data", "돌려받은 결과")
            
            setResult(RESULT_OK, resultIntent) // 결과가 잘 만들어졌다는 신호를 주거나 / 결과를 전달
            finish() // 이 액티비티가 종료될 때 결과를 돌려줌
        }
    }
}