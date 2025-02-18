package com.example.android_animation

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_animation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var isAnimation: AnimationDrawable
    var isStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            img.setImageResource(R.drawable.animation_item)
            isAnimation = img.drawable as AnimationDrawable

            btn.setOnClickListener {
                if(!isStart){
                    isAnimation.start()
                    btn.setBackgroundColor(Color.RED)
                    isStart = true
                    btn.text = "Stop"
                }else{
                    isAnimation.stop()
                    btn.setBackgroundColor(resources.getColor(R.color.green, null))
                    btn.text = "Start"
                    isStart = false
                }
            }

        }



    }
}