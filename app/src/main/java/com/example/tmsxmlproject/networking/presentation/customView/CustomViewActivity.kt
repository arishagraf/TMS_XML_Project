package com.example.tmsxmlproject.networking.presentation.customView

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tmsxmlproject.R
import com.example.tmsxmlproject.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = TextView(this).apply {
            setTextColor(getColor(R.color.purple_light))
            text = getText(R.string.app_name)
            textSize = 23f
            id = View.generateViewId()
            gravity = Gravity.CENTER_HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 100
                bottomMargin = 32
            }
        }

        val button = Button(this).apply {
            setBackgroundColor(getColor(R.color.black))
            setText(getText(R.string.go_to_mvi))
            setTextColor(getColor(R.color.white))
            textSize = 16f
            setOnClickListener {
                Toast.makeText(
                    this@CustomViewActivity,
                    getText(R.string.custom_button_click_text),
                    Toast.LENGTH_SHORT
                ).show()
            }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 100
            }
        }

        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        binding.root.addView(linearLayout)
        linearLayout.addView(textView)
        linearLayout.addView(button)


        binding.customButton.setOnClickListener {
            Toast.makeText(this, getText(R.string.custom_button_text), Toast.LENGTH_SHORT).show()
        }

        val animator = ValueAnimator.ofInt(0, 100)
        animator.duration = 4000
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            binding.tvValueAnimator.text = "$value%"
        }
        animator.start()

        val animatorO = ObjectAnimator.ofFloat(
            binding.btnObjectAnimator, "alpha", 0f, 1f
        )
        animatorO.duration = 5000
        animatorO.start()

        binding.customButton.animation = AnimationUtils.loadAnimation(this, R.anim.anim_button)

//        val textViewLayoutParams = ConstraintLayout.LayoutParams(
//            ConstraintLayout.LayoutParams.WRAP_CONTENT,
//            ConstraintLayout.LayoutParams.WRAP_CONTENT
//        ).apply {
//            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
//            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
//            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
//            topMargin = 100
//        }
//        textView.layoutParams = textViewLayoutParams
//
//        val buttonLayoutParams = ConstraintLayout.LayoutParams(
//            ConstraintLayout.LayoutParams.MATCH_PARENT,
//            ConstraintLayout.LayoutParams.WRAP_CONTENT
//        ).apply {
//            topToTop = textView.id
//            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
//            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
//            topMargin = 100
//        }
//        button.layoutParams = buttonLayoutParams
    }
}