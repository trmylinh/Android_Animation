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
import com.example.android_animation.databinding.ActivityYoutubeTvBinding
import com.example.android_animation.databinding.ActivityYoutubeViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import fr.bmartel.youtubetv.YoutubeTvView
import fr.bmartel.youtubetv.listener.IPlayerListener
import fr.bmartel.youtubetv.model.VideoInfo
import fr.bmartel.youtubetv.model.VideoState


class YoutubeTVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYoutubeTvBinding
    private val TAG: String = YoutubeTVActivity::class.java.getSimpleName()
    private val POSITION_OFFSET: Int = 5
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            youtubetvView.playVideo("pZ1NdE69VTs")

            Log.i(TAG, "videoUrl: ${youtubetvView.getVideoUrl()} - videoId: ${youtubetvView.getVideoId()}")

            youtubetvView.addPlayerListener(object : IPlayerListener {
                override fun onPlayerReady(videoInfo: VideoInfo?) {
                    Log.i(TAG, "onPlayerReady: ${videoInfo?.videoId} - title: ${videoInfo?.title}")
                }

                override fun onPlayerStateChange(
                    state: VideoState?,
                    position: Long,
                    speed: Float,
                    duration: Float,
                    videoInfo: VideoInfo?
                ) {
                    Log.i(TAG, "onPlayerStateChange: ${state?.name}")
                }

            })
        }
    }
}