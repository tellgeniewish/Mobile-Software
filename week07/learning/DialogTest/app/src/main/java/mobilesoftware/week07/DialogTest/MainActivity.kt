package mobilesoftware.week07.DialogTest

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mobilesoftware.week07.DialogTest.databinding.ActivityMainBinding
import mobilesoftware.week07.DialogTest.databinding.DialogInterfaceBinding

class MainActivity : AppCompatActivity() {
    val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var selectedIdx = 0 // 멤버변수로 선언해야 함
    var selectedItems = booleanArrayOf(false, false, false) // 맨 처음에는 선택이 안 된 것이 기본이라서 모두 false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(mainBinding.root)

        mainBinding.button.setOnClickListener {
            val onOkClick = object:DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, whichButton: Int) {
                    Toast.makeText(this@MainActivity, "확인", Toast.LENGTH_SHORT).show()
                } // 강의자료는 람다식으로 표현함
            }

            // val dialogBinding = DialogInterfaceBinding.inflate(layoutInflater) // 버튼 누르자마자
            val builder: AlertDialog.Builder /*타입 생략 ㄱㄴ*/ = AlertDialog.Builder(this).apply {
                /*뒷 부분(.apply)이 안 적히면 builder.setTitle 이런 식으로 호출*/
                setTitle("대화상자 제목")
                //setMessage("대화상자 메시지")
                // 메세지 대신 배열 아이템을 보이게 함
                //setItems(R.array.foods, null) // null -> 아이템 항목을 클릭해도 아무 일 없음
                /*
                setItems(R.array.foods) { // 목록을 출력
                    dialogInterface: DialogInterface?, idx: Int // food의 idx번째
                    ->
                    // 배열은 xml, 즉 리소스에 만들어져 있다.
                    val food = resources.getStringArray(R.array.foods) // R은 아이디를 접근할 때, resources는 직접 자원에 접근할 때
                    Toast.makeText(this@MainActivity, "${food[idx]}", Toast.LENGTH_SHORT).show()
                    Log.d("MainActivity", "${foods[idx]}")
                }
                */
                // setSingleChoiceItems(R.array.foods, selectedIdx, null)
                /*
                setSingleChoiceItems(R.array.foods, selectedIdx) { // (보여줄 배열, 몇 번 째가 선택되어 있는지, 선택했을 때 리스너)
                    dialogInterface: DialogInterface?, idx: Int
                    -> selectedIdx = idx // 선택하는 순간 몇 번 째를 선택했는지를 바꾼다
                }
                */
                // setMultiChoiceItems(R.array.foods, selectedItems, null)
                /*
                setMultiChoiceItems(R.array.foods, selectedItems) { // selectedItems은 boolean 타입의 배열
                    p0: DialogInterface?, idx: Int, isSelected: Boolean
                    -> selectedItems[idx] = isSelected
                }
                */

                //setView(dialogBinding.root) // 내가 만든 화면이 뜬다
                //dialogBinding.etProduct.text

                setIcon(R.mipmap.ic_launcher_round)
                // setPositiveButton("확인", null) // null로 하면 눌렀을 때 닫힘
                //setPositiveButton("확인", onOkClick)
                setPositiveButton("확인") {p0: DialogInterface?, whichButton: Int ->
                    val food = resources.getStringArray(R.array.foods) // 배열을 가져왔음
                    //Toast.makeText(this@MainActivity, "${food[selectedIdx]}", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "${selectedItems.toList()}", Toast.LENGTH_SHORT).show()
                    //Log.d("MainActivity", "${selectedItems.toList()}")
                }
                setNegativeButton("취소", null)
                // setNeutralButton("대기", null)
                /* 첫 번째 매개변수는 "대기", 두 번째 매개변수는 함수
                setNeutralButton("대기", { p0: DialogInterface?, whichButton: Int
                    -> Toast.makeText(this, "대기", Toast.LENGTH_SHORT).show()})
                */
                setNeutralButton("대기") {p0: DialogInterface?, whichButton: Int ->
                            Toast.makeText(this@MainActivity, "대기", Toast.LENGTH_SHORT).show()
                }

                setCancelable(false) // 대화상자 주변을 눌렀을 때 true -> 닫힘 / false -> 대화상자 유지
                // 맨 마지막에 빌더를 반환한다 --> 타입이 빌더임
            }
            builder.show()
            Toast.makeText(this, "계속 수행", Toast.LENGTH_SHORT).show() // Modeless처럼 동작
        }
    }
}