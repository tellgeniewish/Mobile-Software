package ddwu.com.mobile.week06.sample.mycustomview

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.week06.sample.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var selectedIdx = 0 // 멤버변수로 선언해야 함

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        mainBinding.myCustomView.setOnLongClickListener {

            val builder: AlertDialog.Builder /*타입 생략 ㄱㄴ*/ = AlertDialog.Builder(this).apply {
                /*뒷 부분(.apply)이 안 적히면 builder.setTitle 이런 식으로 호출*/
                setTitle("색상 선택")

                setSingleChoiceItems(R.array.color, selectedIdx) { // (보여줄 배열, 몇 번 째가 선택되어 있는지, 선택했을 때 리스너)
                    dialogInterface: DialogInterface?, idx: Int
                    -> selectedIdx = idx // 선택하는 순간 몇 번 째를 선택했는지를 바꾼다
                }

                setPositiveButton("확인") { p0: DialogInterface?, whichButton: Int ->
                    when (selectedIdx) {
                        0 -> mainBinding.myCustomView.paintColor = Color.RED
                        1 -> mainBinding.myCustomView.paintColor = Color.GREEN
                        2 -> mainBinding.myCustomView.paintColor = Color.BLUE
                    }
                    mainBinding.myCustomView.invalidate()
                }

                setNegativeButton("취소", null)

                setCancelable(false) // 대화상자 주변을 눌렀을 때 true -> 닫힘 / false -> 대화상자 유지
                // 맨 마지막에 빌더를 반환한다 --> 타입이 빌더임
            }
            builder.show()

            true
        }
    }

}