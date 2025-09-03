package com.example.tmsxmlproject.networking.presentation.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.example.tmsxmlproject.R

class CustomButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attributeSet, defStyleAttr) {

    val paint = Paint().apply {
        color = context.getColor(R.color.purple_light)
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val oval = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawOval(
            oval,
            paint
        )
        canvas.drawText("text", 0, 0, width.toFloat(), height.toFloat(), paint)
    }

    init {
//        setBackgroundColor(context.getColor(R.color.purple_light))
//        setTextColor(context.getColor(R.color.black))
//        setText(context.getString(R.string.click_text))
//        setPadding(32, 16, 32, 16)
//        setOnClickListener {
//            Toast.makeText(
//                context,
//                context.getString(R.string.custom_button_text),
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }
}