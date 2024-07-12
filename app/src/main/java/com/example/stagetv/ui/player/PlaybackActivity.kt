package com.example.stagetv.ui.player

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.stagetv.databinding.ActivityPlaybackBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaybackActivity : FragmentActivity() {
    private lateinit var binding: ActivityPlaybackBinding
    private lateinit var player: ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Ninja", "PlaybackActivity onCreate()")
        super.onCreate(savedInstanceState)
        binding = ActivityPlaybackBinding.inflate(layoutInflater)
        setContentView(binding.root)


        player = ExoPlayer.Builder(this).build()
        binding.videoView.player = player
        val mediaItem =
            MediaItem.fromUri("https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }


}