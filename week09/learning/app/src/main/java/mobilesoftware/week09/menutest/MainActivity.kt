package mobilesoftware.week09.menutest

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mobilesoftware.week09.menutest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // res > values > themes 에서 <!-- 원래는 DayNight 뒤에 NoActionBar 붙어 있는데 지웠다 -->

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var selected: Int = 3 // 선택된 메뉴 버튼
    var contextSelected: Int = 3 // Context Menu에서 선택된 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Context Menu 등록
        registerForContextMenu(binding.tvText) // id가 아니라 객체를 써야 함
    }

    // 메뉴 생성 시 최초 1회만!!
    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // ctrl + O 상속받은 메소드
        // 매개변수로 빈 Menu가 들어온다

        //menuInflater.inflate(R.menu.menu_main, menu) // A, B : A와 B를 연결시켜 줌
        menuInflater.inflate(R.menu.food_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 메뉴 버튼 누를 때마다 매번 호출됨!!
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean { // 다시 메뉴를 누를 때 이전 선택을 기억함!!
        when (selected) {
            3 -> menu?.findItem(R.id.SubItem03)?.setChecked(true)
            4 -> menu?.findItem(R.id.SubItem04)?.setChecked(true)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // 여러 메뉴 중 하나를 선택했을 때 호출
        when (item.itemId) { // switch문
            R.id.SubItem01 ->
                Toast.makeText(this, "SubItem01", Toast.LENGTH_SHORT).show()

            R.id.SubItem02 ->
                Toast.makeText(this, "SubItem02", Toast.LENGTH_SHORT).show()

            R.id.SubItem03 -> {
                Toast.makeText(this, "SubItem03", Toast.LENGTH_SHORT).show()
                selected = 3
            }
            R.id.SubItem04 -> {
                Toast.makeText(this, "SubItem04", Toast.LENGTH_SHORT).show()
                selected = 4
            }
        }
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        return true
        //return super.onOptionsItemSelected(item)
    }

    // item01을 선택했을 때!!
    fun onMenuClick(item: MenuItem) { // item01의 onClick으로 설정되어 있음!
        // 매개변수로 MenuItem가 들어가고
        // 리턴 타입은 없다
        Toast.makeText(this, "Item", Toast.LENGTH_SHORT).show()

        // 보통 showAsAction 속성처럼 App Bar에 별도로 보이는 메뉴에 사용
        // onOptionsItemSelected보다 우선순위 높음
    }

    // Context Menu는 특정 뷰를 롱클릭 했을 때!! 클릭할 때마다 매번 다시 호출됨
    override fun onCreateContextMenu(
        menu: ContextMenu?, // 빈 메뉴
        v: View?, // View가 매개변수로 들어오는 이유: 어떤 뷰를 롱클릭했는지 알아보기 위해 view.~ 사용하려고
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_main, menu)

        // 클릭할 때마다 매번 다시 호출되기 때문에
        // 메뉴 만들고 코드 작성하면 됨!
        // Context Menu에서는 따로 onPrepareOptionsMenu 함수 없음!
        when (contextSelected) { // 다시 메뉴를 누를 때 이전 선택을 기억함!!
            3 -> menu?.findItem(R.id.SubItem03)?.setChecked(true)
            4 -> menu?.findItem(R.id.SubItem04)?.setChecked(true)
        }

        super.onCreateContextMenu(menu, v, menuInfo)

        /* 여러 개면
        when (v?.id) {
            R.id.tvText -> menuInflater.inflate(R.menu.food_menu, menu)
        }
        */
    }

    // ContextItem 선택했을 때
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) { // switch문
            R.id.SubItem03 -> {
                Toast.makeText(this, "Context03", Toast.LENGTH_SHORT).show()
                contextSelected = 3
            }
            R.id.SubItem04 -> {
                Toast.makeText(this, "Context04", Toast.LENGTH_SHORT).show()
                contextSelected = 4
            }
        }
        Toast.makeText(this, "Context!!!", Toast.LENGTH_SHORT).show()
        return true
        //return super.onContextItemSelected(item)
    }
}