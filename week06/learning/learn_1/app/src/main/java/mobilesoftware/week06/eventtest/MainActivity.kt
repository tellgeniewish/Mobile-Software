package mobilesoftware.week06.eventtest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mobilesoftware.week06.eventtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()/*, View.OnClickListener*/ { // 2-B 권장하지 않음! MainAcitivity 뷰 자체를 관리하는거지, 뷰가 동작하는 기능을 담당하는게 아님!
    val TAG = "MainActivity" // 로그형 태그

    val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater) // binding객체는 xml파일 이름을 기준으로 만들어짐--> ActivityMainBinding
    } //변수 초기화 부분에서 = 사용해서 코드를 대입하면 죽음->by lazy사용해야 함! val mainBinding: ActivityMainBinding by lazy = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        /* // 2-A
        val myClick = MyClick(this)
        mainBinding.myButton.setOnClickListener(myClick)*/

        // 2-B
        //mainBinding.myButton.setOnClickListener(this) // this는 Activity

        /* // 2-C 상속받는 클래스 만들지 않고 바로 객체 만들기
        val myClick = object: View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity, "익명 리스너 인터페이스 구현", Toast.LENGTH_SHORT).show()
            }
        }
        mainBinding.myButton.setOnClickListener(myClick)*/
        // 자바에서 여기까지도 지원됨
        mainBinding.myButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity, "익명 리스너 인터페이스 구현", Toast.LENGTH_SHORT).show()
            }
        })

        // 2-D SAM(Single Abstract Method) 대부분 이 방식 사용함! 코틀린에서 지원함
        mainBinding.myButton.setOnClickListener { //object: View.OnClickListener { 어차피 들어갈 걸 알아서 생략 ㄱㄴ
            //override fun onClick(v: View?) { 어차피 들어갈 걸 알아서 생략 ㄱㄴ
            Toast.makeText(this@MainActivity, "익명 리스너 인터페이스 구현", Toast.LENGTH_SHORT).show()
            
            Log.d(TAG, "익명 리스너 로그 출력") // (태그 문자열, 출력할 문자열)
            // 하단 Logcat -> 왼쪽 버튼 중 Configure Logcat Formmating Options -> Compact View(패키지 이름 생략되고 이름만 나온다)
            // 태그 이름을 필터링을 해주면(Logcat창 상단 깔대기 모양) 내가 찍은 로그만 걸러내서 볼 수 있다

            //}
        //}
        } // 대신 중괄호로 바꿔준다
        mainBinding.mainLayout.setOnLongClickListener{
            Toast.makeText(this@MainActivity, "롱클릭!!", Toast.LENGTH_SHORT).show()
            true// 롱클릭은 boolean타입 반환이 있어야 한다!
            // 여기까지 처리가 끝나서 더이상 신경 안 써도 되면 true(추가적인 이벤트 처리 진행을 생략)
            // 이후에 이어서 처리해야 하면 false(이벤트 처리를 다음 순위의 리스너에게 이벤트 진행)
            // 우선순위
            // 1. 2-D SAM 이벤트 리스너
            // 2. 콜백 메소드(커스텀 뷰 사용 시) -> 상속 받는 방식
            // 3. 액티비티 콜백 메소드
        }
    }
    // 2-A와 2-B 방법의 이유: 인터페이스는 직접 객체를 만들 수가 없어서
    // 인터페이스가 객체를 만들지 못하는 이유: 함수 내용물이 없어서
    // 객체를 만드는 순간 함수 내용물을 채우기

    /* // 2-A
    class MyClick(val context: Context): View.OnClickListener { // View.OnClickListener가 인터페이스라서 MyClick 클래스로 구현함
        override fun onClick(v: View?) {
            Toast.makeText(context, "리스너 인터페이스 구현 클래스", Toast.LENGTH_SHORT).show()
        }
    }*/
    /* // 2-B
    override fun onClick(v: View?) {
        Toast.makeText(this, "액티비티에서 구현", Toast.LENGTH_SHORT).show()
    }*/
}