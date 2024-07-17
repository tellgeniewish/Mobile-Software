package ddwu.com.mobile.week06.sample.mycustomview

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.week06.sample.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        // 2. MainActivity 에 익명 내부 클래스의 임시객체 구현으로 1번과 동일한 기능 작성
        // • OnTouchListener 사용, MyCustomView의 onTouchEvent(…) 는 주석처리 후 구현
        val myTouch = object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                mainBinding.myCustomView.posX = event!!.x
                mainBinding.myCustomView.posY = event!!.y
                mainBinding.myCustomView.invalidate()
                return false
            }
        }
        mainBinding.myCustomView.setOnTouchListener(myTouch)

        // 3. 롱클릭할 경우 원의 색상이 바뀌는 기능을 익명 내부 클래스의 임시 객체 구현 방법으로 작성
        // • OnLongClickListener 구현
        // • 앞서 작성한 onTouchListener 의 onTouch(..)의 반환값은 false 로 변경)
        // • 좌표를 바꿨을 때 한번만 실행하도록 구현
        val myLongTouch = object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                if (mainBinding.myCustomView.paintColor == Color.RED)
                    mainBinding.myCustomView.paintColor = Color.BLUE
                else
                    mainBinding.myCustomView.paintColor = Color.RED
                //mainBinding.myCustomView.paintColor = Color.BLUE
                return true
            }
        }
        mainBinding.myCustomView.setOnLongClickListener(myLongTouch)
    }
}