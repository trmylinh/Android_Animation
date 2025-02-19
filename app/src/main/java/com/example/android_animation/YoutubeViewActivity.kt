package com.example.android_animation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.android_animation.databinding.ActivityMainBinding
import com.example.android_animation.databinding.ActivityYoutubeViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import fr.bmartel.youtubetv.YoutubeTvView
import fr.bmartel.youtubetv.listener.IPlayerListener
import fr.bmartel.youtubetv.model.VideoInfo
import fr.bmartel.youtubetv.model.VideoState


class YoutubeViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYoutubeViewBinding
    private val TAG: String = YoutubeViewActivity::class.java.getSimpleName()
    private val POSITION_OFFSET: Int = 5
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycle.addObserver(video1)

//            val parentView = video1.parent as? ViewGroup
//            parentView?.removeView(video1)
//
//            val customUi = video1.inflateCustomPlayerUi(R.layout.custom_youtube_ui)
//            video1.setCustomPlayerUi(customUi)
//
//            val playPauseButton = customUi.findViewById<ImageView>(R.id.playPauseButton)
//            val seekBar = customUi.findViewById<SeekBar>(R.id.seekBar)

            video1.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    Log.i(
                        TAG,
                        "onReady"
                    )
                    val videoId = "pZ1NdE69VTs"
                    youTubePlayer.loadVideo(videoId, 0f)

//                    playPauseButton.setOnClickListener {
//                        if (isPlaying) {
//                            youTubePlayer.pause()
//                        } else {
//                            youTubePlayer.play()
//                        }
//
//                    }

//                    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//                        override fun onProgressChanged(
//                            seekBar: SeekBar?,
//                            progress: Int,
//                            fromUser: Boolean
//                        ) {
//                            if (fromUser) {
//                                youTubePlayer.seekTo(progress.toFloat())
//                            }
//                        }
//
//                        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//
//                        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
//                    })

                    youTubePlayer.removeListener(this)

                }

                override fun onStateChange(
                    youTubePlayer: YouTubePlayer,
                    state: PlayerConstants.PlayerState
                ) {
                    when (state) {
                        PlayerConstants.PlayerState.PLAYING -> {
                            Log.d(TAG, "Đang phát")
//                            isPlaying = true
//                            updatePlayPauseIcon(playPauseButton, isPlaying)
                        }
                        PlayerConstants.PlayerState.PAUSED -> {
                            Log.d(TAG, "Đã tạm dừng")
//                            isPlaying = false
//                            updatePlayPauseIcon(playPauseButton, isPlaying)
                        }
                        PlayerConstants.PlayerState.ENDED -> Log.d(TAG, "Đã phát xong")
                        else -> {}
                    }
                }
            })
        }

        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

    }

    private fun updatePlayPauseIcon(button: ImageView, isPlaying: Boolean) {
        val icon = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        button.setImageResource(icon)
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