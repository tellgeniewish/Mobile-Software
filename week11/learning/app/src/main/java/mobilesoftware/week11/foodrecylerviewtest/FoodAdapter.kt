package mobilesoftware.week11.foodrecylerviewtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mobilesoftware.week11.foodrecylerviewtest.databinding.ListItemBinding

class FoodAdapter(val foods: ArrayList<FoodDto>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun getItemCount(): Int = foods.size // 원본 데이터 개수를 반환
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return FoodViewHolder(itemView)
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
//        holder.photo.setImageResource(foods[position].photo)
//        holder.food.text = foods[position].food
//        holder.count.text = foods[position].count.toString() // text에 숫자값을 리소스 연결시키는데 바로 쓰면 안 됨!
        // 숫자값을 쓰면 리소스의 아이디로 착각해서 리소스에서 찾는데 그런 아이디는 없어서 오류날 수 있기 때문에

        holder.itemBinding.ivPhoto.setImageResource(foods[position].photo)
        holder.itemBinding.tvFood.text = foods[position].food
        holder.itemBinding.tvCount.text = foods[position].count.toString()
    }

//    inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val photo = view.findViewById<ImageView>(R.id.ivPhoto)
//        val food = view.findViewById<TextView>(R.id.tvFood)
//        val count = view.findViewById<TextView>(R.id.tvCount)
//    }
    // findViewById를 사용하는 것보다 Binding 객체를 사용하는 것이 속도(스크롤할 때 화면을 띄우는)가 훨씬 빠르다
    inner class FoodViewHolder(/*view: View*/ val itemBinding: ListItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        val TAG = "FoodViewHolder"
        init {
            itemBinding.root.setOnClickListener{
                // 로그에 문자열로 찍히는 값은 foods[adapterPosition] 입니다.
                // 이는 foods 리스트 adpaterPosition 위치에 있는 하나의 FoodDto 객체가 될 것입니다.
                // 풀어서 표현해보면
                // val foodDto = foods[adapterPosition]
                // Log.d(TAG, "${ foodDto }")가 될 것입니다.
                // 문자열로 출력하여야 하니 foodDto의 toString() 이 호출됨
                Log.d(TAG, "${foods[adapterPosition]}") // FoodDto의 toString형식으로
                // 만약 DB 사용 시 여기서 연결시켜야 함 -> 원본 데이터를 바꾸기 위해
                // 하지만 여기서 DB 연결하지 않는 것이 좋다! -> 코드가 복잡해져서
                // MainActivity에서 연결하는 것이 좋음
                // 코틀린에서는 람다 함수를 사용해서 매개변수로 함수를 넣어줄 수 있다 = 고차 함수
                listener.onItemClick(it, adapterPosition) // 실제 기능 여기 X. MainActivity에 구현!
            }
            itemBinding.root.setOnLongClickListener{
                longListener.onItemLongClick(it, adapterPosition)
                // true // 인터페이스에서 불린값 반환하므로 없어도 됨
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int): Unit
    }
    lateinit var listener: OnItemClickListener
    fun setOnItemClickListener(listener: OnItemClickListener) { // 리스너를 외부에서 받아서 멤버변수에 저장
        this.listener = listener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }
    lateinit var longListener: OnItemLongClickListener
    fun setOnItemLongClickListener(longListener: OnItemLongClickListener) {
        this.longListener = longListener
    }
}