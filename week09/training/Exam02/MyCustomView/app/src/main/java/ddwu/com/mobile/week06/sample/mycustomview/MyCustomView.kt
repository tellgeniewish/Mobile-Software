package ddwu.com.mobile.week06.sample.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

const val TAG="MyCustomView"

class MyCustomView : View {
    var posX : Float
    var posY : Float = 200f
    var paintColor : Int
    var radius = 100f

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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.LTGRAY)
        //val w = canvas.width / 2f
        //val h = canvas.height / 2f
        val paint = Paint()
        paint.setColor(paintColor)

        // 원을 중앙에 보이도록
        posX = canvas.width / 2f
        posY = canvas.height / 2f

        canvas?.drawCircle(posX, posY, radius, paint)
        Log.d (TAG, "($posX, $posY)")
    }
}