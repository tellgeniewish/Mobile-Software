package mobilesoftware.week10.recyclerviewtest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// 매개변수는 필수(X) 그때그때 필요한 것들로!
class MyAdapter(val context: Context, val layout: Int, val list: ArrayList<String>)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>()/*RecyclerView.Adapter를 상속받으므로 여기가지 적고 ViewHolder 생성한 후 <>에 적는다*/ {

    // 사용되는 순서
    override fun getItemCount(): Int { // 화면 몇 개 만들어줄거야? --> 내가 만들 원본 데이터 개수 반환하면 됨
        return list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // 데이터의 개수만큼 호출되는 것 아님!
        val view = LayoutInflater.from(context).inflate(layout, parent, false)

        Log.d("MyViewHolder", "onCreateViewHolder") // 몇 번 호출되는지 체크 ㄱㄴ

        return MyViewHolder(view) // MyViewHolder가 layout을 보관함
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { // position은 몇 번째인지
        holder.tvText.text = list[position] // 화면모양(MyViewHolder(view))과 list를 결합

        Log.d("MyViewHolder", "onBindViewHolder") // 몇 번 호출되는지 체크 ㄱㄴ
    }

    // inner 붙이기 전: init 안에서 this@ 하면 MyViewHolder가 뜬다
    // inner 붙인 후: init 안에서 this@ 하면 MyAdapter가 뜬다
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) { // 화면의 요소를 보관
        // MyViewHolder는 데이터의 개수만큼 만들어지지 않는다!
        // 화면에 보여줄 수 있을만큼 약간의 여유를 가지고 만들어진다 약 5~6개 이상
        val tvText = view.findViewById<TextView>(R.id.tvText)
        
        // 이벤트
        init {
            view.setOnClickListener{// view는 한 칸!
                Toast.makeText(view.context, "항목 $adapterPosition View 터치!", Toast.LENGTH_SHORT).show()
                // adapterPosition은 현재 선택한 항목의 index를 확인할 수 있는 ViewHolder의 멤버변수(getAdapterPosition())
            }
            view.setOnLongClickListener {
                list.removeAt(adapterPosition)
                this@MyAdapter.notifyDataSetChanged()
                true // boolean을 반환해야 함
            }
            tvText.setOnClickListener{
                Toast.makeText(view.context, "TextView Click!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}