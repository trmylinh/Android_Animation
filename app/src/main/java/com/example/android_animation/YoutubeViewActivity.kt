package com.example.android_animation

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.android_animation.databinding.ActivityMainBinding
import com.example.android_animation.databinding.ActivityYoutubeViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import fr.bmartel.youtubetv.YoutubeTvView
import fr.bmartel.youtubetv.listener.IPlayerListener
import fr.bmartel.youtubetv.model.VideoInfo
import fr.bmartel.youtubetv.model.VideoState


class YoutubeViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYoutubeViewBinding
    private val TAG: String = YoutubeViewActivity::class.java.getSimpleName()
    private lateinit var youTubePlayer: YouTubePlayer
    private var isFullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        binding.apply {
            lifecycle.addObserver(youtubePlayerView)

            youtubePlayerView.addFullscreenListener(object : FullscreenListener {
                override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                    isFullScreen = true
                    fullScreenContainer.visibility = View.VISIBLE
                    fullScreenContainer.addView(fullscreenView)

                    // Full Screen remove status bar and navigation bar
                    WindowInsetsControllerCompat(window!!,findViewById(R.id.rootView)).apply {
                        hide(WindowInsetsCompat.Type.systemBars())
                        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }

                    if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                    }
                }

                override fun onExitFullscreen() {
                    isFullScreen = false
                    fullScreenContainer.visibility = View.GONE
                    fullScreenContainer.removeAllViews()

                    // status bar and navigation bar
                    WindowInsetsControllerCompat(window!!,findViewById(R.id.rootView)).apply {
                        show(WindowInsetsCompat.Type.systemBars())
                    }
                    if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR){
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                    }

                }
            })

            val youtubePlayerListener = object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    this@YoutubeViewActivity.youTubePlayer = youTubePlayer
                    val videoId = "pZ1NdE69VTs"
                    youTubePlayer.loadOrCueVideo(lifecycle,videoId, 0f)
                }
            }

            val iFramePlayerOptions = IFramePlayerOptions.Builder()
                .controls(1)
                .fullscreen(1)
                .build()

            youtubePlayerView.enableAutomaticInitialization = false
            youtubePlayerView.initialize(youtubePlayerListener,iFramePlayerOptions)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (!isFullScreen){
                youTubePlayer.toggleFullscreen()
            }
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if (isFullScreen){
                youTubePlayer.toggleFullscreen()
            }
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isFullScreen){
                youTubePlayer.toggleFullscreen()
            }else{
                finish()
            }
        }
    }
}