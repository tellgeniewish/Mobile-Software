package mobilesoftware.week12.activitytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mobilesoftware.week12.activitytest.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val subBinding by lazy {
        ActivitySubBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(subBinding.root)
    }
}