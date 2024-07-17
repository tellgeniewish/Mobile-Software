package week04.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onMyClick(view: View) { // 반드시 View객체가 들어와야
        //Toast.makeText(this, "버튼을 클릭함", Toast.LENGTH_SHORT).show()
        var myName : EditText = findViewById(R.id.etName)
        val name : String = myName.text.toString()
        var myPhoneNum : EditText = findViewById(R.id.etPhone)
        val phoneNum : String = myPhoneNum.text.toString()

        Toast.makeText(this, "안녕하세요, 저는 ${name} 입니다. 전화번호는 ${phoneNum} 입니다.", Toast.LENGTH_SHORT).show()
    }
    fun exit(view: View) {
        finish()
    }
}