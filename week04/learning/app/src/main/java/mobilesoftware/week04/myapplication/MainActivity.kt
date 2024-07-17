package mobilesoftware.week04.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// 실제로 코드가 동작해서 앱이 실행되는 곳(activity_main.xml를 가져와서!)
class MainActivity : AppCompatActivity() { // AppCompat은 호환성을 위한 것 / Activity를 상속받고 있음
    // 안드로이드의 주요 구성요소 4가지
    // 화면을 담당하는 Activity
    // 백그라운드에서 동작하는 Service
    // 외부에서 정보를 전달하는 Content Provider
    // 방송을 수신하는 Broadcast Receiver

    // 화면은 xml로 정의함
    // activity_main.xml: 화면에 대한 설계 정보 = 설계도
    // 컴파일 -> 빌드 -> 자동으로 자바 객체(코틀린 코드)로 만들어진다
    // res 파일 객체들의 id가 R이라는 파일에 등록됨
    // R.class는 객체들의 id만 정수형 상수 값으로 가짐(자동으로 생성)
    // R은 리소스라는 뜻! 즉, res

    // drawable과 mipmap은 둘 다 이미지와 관련된 폴더!
    // 앱에 배경그림을 넣고 싶을 때, 이미지를 drawable 폴더에 복사해서 붙여넣는다
    // mipmap은 아이콘 사진!
    // values의 strings.xml은 문자열 자원으로 앱 이름 정보가 manifest에 있다!(manifest의 코드를 누르면 strings.xml로 이동함)

    // menu 폴더는 없음! 만들어서 쓴다
    
    // Gradle은 앱 자체는 아님!
    // 꼭 Gradle을 사용해야 하는 것 아님!
    // Module :app에는 이 앱을 컴파일할 때 어떤 sdk를 써야하는지, 어떤 정보와 라이브러리를 포함시켜야 하는지 기록되어 있다
    // dependencies{...}에 명령어를 사용하면 사용하고 싶은 패키지를 자동으로 내려받아줌
    
    // 안드로이드는 구조적으로 메인 함수가 존재하지 않음!
    // 많은 Activity 화면 중에 무엇을 먼저 실행할 것인가? 하는 정보는 menifest에 있음(intent-filter)
    // menifest 앱의 자기소개서
    // 앱에 대한 아이콘 클릭하는 순간 안드로이드 운영체제가 그 앱을 찾아서 menifest를 들여다보고 실행시킬 Activity를 찾음
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // 설계도

        // TextView 생략하면 오류나는 이유
        // findViewById는 View를 찾아줌 -> 다운캐스팅 해줘야 함
        //val myText : TextView = findViewById(R.id.myText) // 타입을 알려주거나
        // val myText = findViewById<TextView>(R.id.myText) // 타입을 지정해주거나
        //myText.setText("Hi!!!") // 멤버변수는 setter, getter 존재

        /*
        // setContentView(R.layout.activity_main) 주석 처리 ㄱ. ㄱ
        // 원래는 이렇게 직접 객체를 만들어야 함 --> 코드만을 사용해서 화면 구현도 ㄱㄴ
        // TextView는 xml 태그 이름 = 클래스 이름
        val textView : TextView = TextView(this) // alt + Enter 누르면 임포트 ㄱㄴ
        textView.setText("코드로 문자열 출력")
        setContentView(textView)
        // xml 화면 구현 장점
        // 개발자, 디자이너 분담 작업 용이
        // 교체 ㄱㄴ -> 호환성, 국제화
        // 소스 코드 교체하는거 아님 -> 컴파일할 필요 x
        // 레이아웃 재활용 ㄱㄴ
        */

        // View = Widget(공식 용어 아님) + ViewGroup
        // View를 관리하는 클래스 = Activity

        // 자바의 최상위 클래스 = Object
        // 코틀린 최상위 클래스 = Any
        
        // Object는 View의 부모 클래스
        // View는 ViewGroup의 부모 클래스
        
        // View를 화면에 배치할 때는 상or하 & 좌or우 중 하나씩은 연결이 되어 있어야 한다
        // 모든 View는 id를 가질 수 있다
        // (xml 코드)새로 아이디를 지정할 때는 @+id/지정한 id
        // 기본 @id/지정한 id

        // layout_width(가로) & layout_height(세로)
        // match_parent: 화면에 꽉 참(항목의 너비를 상위의 객체에 사이즈로 맞춤)
        // wrap_content: 글자를 감쌀 수 있는 사이즈
        // 원하는 크기로 지정 가능: dp 단위(폰마다 해상도가 다름 -> px을 사용하면 크기가 둘쭉날쭉함 -> 밀도를 이용함)
        
        // textSize: sp 단위
        // background: 배경색
        // padding, visibility(visible/invisible/gone자리만 차지함), clickable/longClickable, focusable, ...
        // 이런 속성을 모든 Widget과 ViewGroup이 다 가지고 있다(View가 상속해주기 때문)
        
        // xml의 text로 내용을 고치는 것보다 res>values>strings.xml 권장
        // text 옆의 버튼(Pick a Resource) 누르면 입력한 string정보 선택 ㄱㄴ

        // ImageView 사용 시 사진이 필요
        // 사진의 이름은 영어 소문자, 숫자, 밑줄 사용 ㄱㄴ (대문자, 한글 오류) -> 모든 res 폴더 내 이름이 그러함
        // 확장자는 jpg 또는 png(가능하면 png 권장 -> 투명도 조절 ㄱㄴ)
        // 사진을 ctrl 누르면서 drawble에 옮기면 copy할거냐는 창이 뜬다
        // ImageView 끌어다 놓으면 drawble에 복사해놓은 사진 지정 ㄱㄴ
        // xml 코드 보면, srcCompat에서 확인 ㄱㄴ -> 확장자 사라짐 -> 원래 abc.jpg와 abc.png는 다른 파일이지만 여기 넣었을 때 구분 불가! -> 반드시 이름 자체가 달라야 함
        // 원래는 src임! 하위호환성을 위해 확장됨, srcCompat은 호환성을 위한 것
    }
    
    fun onMyClick(view: View) { // 반드시 매개변수로 View객체가 들어와야 함 + 반환 타입이 없다(Unit 생략됨)
        //Toast.makeText(this, "버튼을 클릭함", Toast.LENGTH_SHORT).show() // this는 Activity 자신! 원래는 context를 넣어줘야 하는데 Activity가 context 역할해줄 수 있어서!
        // 버튼의 속성 중 onClick에서 onMyClick함수를 연결시키면 됨

        val myEdit : EditText = findViewById(R.id.myEdit)
        // text: EditText 입출력 텍스트
        // hint: 입력할 값 안내 ㄱㄴ, 입력 시 자동으로 사라짐
        // inputType: 키보드 지정 ㄱㄴ(글자,숫자,날짜,전화번호 / 패스워드 여부도 지정 ㄱㄴ)
        //val text = myEdit.text // == myEdit.getText()처럼 getter 사용 ㄱㄴ하지만 바로 속성 이름 써도 된다 // setter는 생략 불가!

        // myEdit.text는 String이 아님! Editable이라는 클래스!
        // val text : String = myEdit.text 사용 시 에러남
        // toString() 쓰면 문자열로 바꿔서 사용 ㄱㄴ
        // val text : String = myEdit.text.toString()

        val myText : TextView = findViewById(R.id.myText)
        myText.text = myEdit.text

        Toast.makeText(this, "버튼을 클릭함", Toast.LENGTH_SHORT).show()
    }
}