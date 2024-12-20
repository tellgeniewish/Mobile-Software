package mobilesoftware.week05.myviewtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyOuterView: View {
    // 내가 만든 MyOuterView를 xml에 포함시키려면 생성자를 3개 만들어야 한다
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var color = Color.BLUE

    var newX = 200.toFloat() // View에는 기본적으로 x 속성이 있어서 변수 이름을 x로 지으면 안 됨
    var newY = 200.toFloat()
    var newR = 100.toFloat()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.LTGRAY)
        val paint = Paint()
        //paint.setColor(Color.BLUE)
        paint.setColor(color)
        canvas?.drawCircle(newX, newY, newR, paint) // x좌표, y좌표, 반지름
    }
}