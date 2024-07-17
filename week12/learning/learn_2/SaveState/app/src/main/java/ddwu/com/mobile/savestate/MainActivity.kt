package ddwu.com.mobile.savestate

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import ddwu.com.mobile.savestate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) { // savedInstanceState: Bundle? 복원된 인스턴스
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myView.x = savedInstanceState?.getInt("x") ?: 100
        Log.d(TAG, "Main: (${binding.myView.x}, ${binding.myView.y})")
        val pref = getSharedPreferences("save_state", 0)
        binding.myView.y = pref.getInt("y", 300) // y 값을 꺼낸다, 없으면 300
    }

    val TAG = "MainActivity"

    override fun onPause() { // 보관할 때
        super.onPause()
        val pref : SharedPreferences = getSharedPreferences("save_state", 0) // 안드로이드에서 자료 저장 시 --> DB, 파일, SharedPreferences 영구적으로 보관
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putInt("y", binding.myView.y)
        editor.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) { // (앱 실행될 때만) 인스턴스 값을 보관
        super.onSaveInstanceState(outState)
        outState.putInt("x", binding.myView.x)
        outState.putInt("y", binding.myView.y)
        Log.d(TAG, "Save: (${binding.myView.x}, ${binding.myView.y})")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // (앱 실행될 때만) 인스턴스 값을 복원
        super.onRestoreInstanceState(savedInstanceState)
        binding.myView.x = savedInstanceState.getInt("x")
        binding.myView.y = savedInstanceState.getInt("y")
        Log.d(TAG, "Restore: (${binding.myView.x}, ${binding.myView.y})")
    }
}