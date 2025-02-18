package com.example.android_animation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.android_animation.databinding.ActivityMainBinding
import com.example.android_animation.databinding.ActivityYoutubeViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import fr.bmartel.youtubetv.YoutubeTvView
import fr.bmartel.youtubetv.listener.IPlayerListener
import fr.bmartel.youtubetv.model.VideoInfo
import fr.bmartel.youtubetv.model.VideoState


class YoutubeViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYoutubeViewBinding
    private val TAG: String = YoutubeViewActivity::class.java.getSimpleName()
    private val POSITION_OFFSET: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
//            playButton.setOnClickListener {video1.start() }
//            pauseButton.setOnClickListener { video1.pause() }
//            nextButton.setOnClickListener { video1.nextVideo() }
//            previousButton.setOnClickListener { video1.previousVideo() }
//            backwardButton.setOnClickListener { video1.moveBackward(POSITION_OFFSET) }
//            forwardButton.setOnClickListener { video1.moveForward(POSITION_OFFSET) }

//            video1.playVideo("6pxRHBw-k8M")

            video1.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    Log.i(
                        TAG,
                        "onReady"
                    )
                    val videoId = "6pxRHBw-k8M"
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.removeListener(this)
                }
            })
        }

        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //showing dialog and then closing the application..
            Log.i(
                TAG,
                "handleOnBackPressed"
            )
        }
    }
}