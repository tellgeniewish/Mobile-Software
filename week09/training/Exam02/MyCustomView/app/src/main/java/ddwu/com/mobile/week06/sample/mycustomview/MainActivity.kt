package ddwu.com.mobile.week06.sample.mycustomview

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.week06.sample.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var contextSelected: Int = 1 // Context Menu에서 선택된 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerForContextMenu(binding.myCustomView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.size_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) { // switch문
            R.id.bigger -> binding.myCustomView.radius += 100f
            R.id.smaller -> binding.myCustomView.radius -= 100f
        }
        binding.myCustomView.invalidate()

        return true
        //return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.color_menu, menu)

        when (contextSelected) {
            1 -> menu?.findItem(R.id.red)?.setChecked(true)
            2 -> menu?.findItem(R.id.green)?.setChecked(true)
            3 -> menu?.findItem(R.id.blue)?.setChecked(true)
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) { // switch문
//            R.id.red -> binding.myCustomView.paintColor = Color.RED
//            R.id.green -> binding.myCustomView.paintColor = Color.GREEN
//            R.id.blue -> binding.myCustomView.paintColor = Color.BLUE
//        }
//        binding.myCustomView.invalidate()

        when (item.itemId) {  // switch문
            R.id.red -> {
                contextSelected = 1
                binding.myCustomView.paintColor = Color.RED
            }
            R.id.green -> {
                contextSelected = 2
                binding.myCustomView.paintColor = Color.GREEN
            }
            R.id.blue -> {
                contextSelected = 3
                binding.myCustomView.paintColor = Color.BLUE
            }
        }
        binding.myCustomView.invalidate()

        return true
        //return super.onContextItemSelected(item)
    }
}