package mobilesoftware.week05.layouttest

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mobilesoftware.week05.layouttest.databinding.LinearLayoutBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        //setContentView(R.layout.linear_layout) // 레이아웃 지정
        // 레이아웃은 고정되어 있지 않다! 실행 중 바꿔치기 ㄱㄴ

//        val button = findViewById<Button>(R.id.button1)
//        button.setOnClickListener {
//            val layout: LinearLayout = findViewById(R.id.linear)
//
//            if (layout.orientation == LinearLayout.VERTICAL) // 수직이면
//                layout.orientation = LinearLayout.HORIZONTAL // 수평으로
//            else
//                layout.orientation = LinearLayout.VERTICAL
//        }

        // findViewById() 대신 viewBinding을 사용할 때는
        // Gradle > build.gradle.kts (Module app)의 andriod{ }에
        // viewBinding {
        //        enable=true
        //    } 을 추가하고 Sync Now해야 함
        val binding: LinearLayoutBinding // LinearLayoutBinding은 Sync하면 자동으로 생김
                = LinearLayoutBinding.inflate(layoutInflater)
        // 오버로딩
        setContentView(binding.root) // root == <LinearLayout>

        binding.button1.setOnClickListener {
            if (binding.linear.orientation == LinearLayout.VERTICAL) // 수직이면
                binding.linear.orientation = LinearLayout.HORIZONTAL // 수평으로
            else
                binding.linear.orientation = LinearLayout.VERTICAL
        }

    }
}