package mobilesoftware.week11.foodrecylerviewtest

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import mobilesoftware.week11.foodrecylerviewtest.databinding.ActivityMainBinding
import mobilesoftware.week11.foodrecylerviewtest.databinding.ListItemBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 데이터 준비
        val foods = FoodDao().foods
        val weathers = WeatherDao().weathers

        // 어댑터 생성
        val adapter = FoodAdapter(foods)
        //val adapter = CustomAdapter(weathers)

        // 레이아웃매니져 생성 및 설정
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // Recylerview에 레이아웃매니져 및 어댑터 설정
        binding.recylerview.layoutManager = layoutManager
        binding.recylerview.adapter = adapter

        // 함수의 형식을 미리 정해놓기 위해 인터페이스 정의
        val listener = object: FoodAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) { // 실제 기능은 MainActivity에!
                //TODO("Not yet implemented")
                Toast.makeText(this@MainActivity, "${foods[position]}", Toast.LENGTH_SHORT).show()
                //foods.removeAt(position)
                //adapter.notifyDataSetChanged()
            }
        }
        adapter.setOnItemClickListener(listener)

        val longListener = object: FoodAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int): Boolean {
                Toast.makeText(this@MainActivity, "${foods[position]} 삭제", Toast.LENGTH_SHORT).show()

                val builder = AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("확인 누르면 삭제됨!!")
                    setMessage("${foods[position]} 삭제하시겠습니까?")
                    setIcon(R.mipmap.ic_launcher_round)
                    setPositiveButton("확인")
                    { p0: DialogInterface?, whichButton: Int
                        -> foods.removeAt(position)
                        // [확인]을 누르고 삭제된 화면을 곧이어 바로 보고 싶으면 여기에 adapter.notifyDataSetChanged() 코드를 작성해야 한다
                        adapter.notifyDataSetChanged() // 이 코드를 주석 처리하고 builder.show() 이후에 같은 코드를 넣으면 [확인]을 눌러도 삭제된 화면이 보이지 않는다
                    }
                    setNegativeButton("취소", null) // null로 하면 눌렀을 때 닫힘
                    setCancelable(false) // 대화상자 주변을 눌렀을 때 true -> 닫힘 / false -> 대화상자 유지
                }
                builder.show()
                Toast.makeText(this@MainActivity, "언제 실행되는지 확인해보세요", Toast.LENGTH_SHORT).show()

                //foods.removeAt(position)
                //adapter.notifyDataSetChanged() // setPositiveButton의 같은 코드를 주석 처리하고 이 코드를 사용하면 삭제해도 바뀐 화면이 바로 보이지 않음
                // 코드 상으로는 대화상자를 띄우고 바로 삭제한 후 notify 를 시키는 것처럼 보이나
                // 대화상자를 띄운 후 삭제할 시점에는 이미 바깥쪽에 있는 notify 가 실행된 상태
                // 이유: 대화상자가 화면에 나타나도 실행 흐름을 막지 않는 modeless 와 비슷한 방식으로 실행되기 때문에
                // 대화상자를 화면에 표시한 후 그 이하의 다른 코드(notify)들은 바로 실행되어 롱클릭을 종료한 상태가 됨

                // 확인을 해보려면 builder.show() 이후에 Toast를 추가해서 대화상자가 나타난 후 확인을 누르기 전 Toast 가 실행되는 것을 보면 됨
                return true
            }
        }
        adapter.setOnItemLongClickListener(longListener)

        binding.button.setOnClickListener{
            foods.add(FoodDto(R.drawable.food01, "${binding.editText.text}", 10))
            adapter.notifyDataSetChanged()
        }
    }
}