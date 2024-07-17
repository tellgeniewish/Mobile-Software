package ddwu.com.mobile.week06.sample.exam01

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.week06.sample.exam01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*viewBinding 관련 부분 작성할 것*/
    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        // 1. 별도의 리스너 인터페이스 구현 클래스 작성
        val myClick = MyClick(this)
        mainBinding.btnOutput.setOnClickListener(myClick)

        /*// 2. 익명 객체 구현으로 작성
        val myClick = object: View.OnClickListener {
            override fun onClick(v: View?) {
                mainBinding.tvDisplay.text = mainBinding.etInput.text
                //Toast.makeText(this@MainActivity, "익명 리스너 인터페이스 구현", Toast.LENGTH_SHORT).show()
            }
        }
        mainBinding.btnOutput.setOnClickListener(myClick)*/

        /*// SAM
        mainBinding.btnOutput.setOnClickListener {
            mainBinding.tvDisplay.text = mainBinding.etInput.text
        }*/
    }
    // 1. 별도의 리스너 인터페이스 구현 클래스 작성
    inner /*inner 안 쓰면 별도의 클래스로 분리되어 mainBinding 사용 불가*/class MyClick(val context: Context): View.OnClickListener { // View.OnClickListener가 인터페이스라서 MyClick 클래스로 구현함
        override fun onClick(v: View?) {
            mainBinding.tvDisplay.text = mainBinding.etInput.text
        }
    }
}