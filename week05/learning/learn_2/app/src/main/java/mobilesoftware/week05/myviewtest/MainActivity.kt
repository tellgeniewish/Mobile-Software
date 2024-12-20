package mobilesoftware.week05.myviewtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mobilesoftware.week05.myviewtest.databinding.ActivityMainBinding
import java.util.Random

// MainActivity 객체는 Context를 상속받음
// Context는 앱이 동작하고 있는 상황, 상태, 문맥을 저장하는 클래스
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        //val myView = MyView(this) // MainActivity객체가 Context를 상속받아서
//        val myView = MyOuterView(this)
//        setContentView(myView)

//        setContentView(R.layout.activity_main)
        // 내가 만든 클래스(커스텀뷰)를 추가하려면 xml 코드에 풀패키지로 직접 작성해야 한다
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            binding.MyOuterView.color = Color.YELLOW

            // x, y 좌표 랜덤 생성 후 변경
            val random = Random() // Random 객체 생성 (import 필요)

            // 0 ~ 500 생성
            val x = random.nextInt(500)
            val y = random.nextInt(500)

            // 100 ~ 200 생성
            val r = random.nextInt(500)

            binding.MyOuterView.newX = x.toFloat()
            binding.MyOuterView.newY = y.toFloat()
            //binding.MyOuterView.newR = r

            binding.MyOuterView.invalidate() // onDraw() 불러올 수 없음 -> 화면을 무효화시키는 커스텀뷰.invalidate() 호출
        }
    }

//    class MyView(context: Context?): View(context) {
//        override fun onDraw(canvas: Canvas) {
//            super.onDraw(canvas)
//            canvas?.drawColor(Color.LTGRAY)
//            val paint = Paint()
//            paint.setColor(Color.BLUE)
//            canvas?.drawCircle(200.toFloat(), 200.toFloat(), 100.toFloat(), paint) // x좌표, y좌표, 반지름
//        }
//    }
}