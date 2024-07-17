package ddwu.com.mobile.week06.sample.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

const val TAG="MyCustomView"

class MyCustomView : View {
    var posX : Float
    var posY : Float = 200f
    var paintColor : Int

    init {
        posX = 200f
        paintColor = Color.RED
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /*// 1. MyCustomView 클래스의 onTouchEvent(..) 메소드를 재정의하여 터치한 위치에 원 표시
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        posX = event!!.x // posX에는 float형만 들어가야 해서 event?.x를 대입하려고 하면 에러가 난다, 따라서 null이 아니라는 의미의 !! 안 붙이면 에러
        //event?.x // ?를 안 붙이면 에러
        posY = event!!.y
        invalidate()
        return true // true를 반환하면 여기서 끝, false를 반환하면 계속
    }*/

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.LTGRAY)
        val paint = Paint()
        paint.setColor(paintColor)
        canvas?.drawCircle(posX, posY, 100f, paint)
        Log.d (TAG, "($posX, $posY)")
    }
}