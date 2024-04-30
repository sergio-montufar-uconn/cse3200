package com.example.lab_6

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_6.ViewModels.RadioViewModel
import com.example.lab_6.ViewModels.VideoViewModel
import com.example.lab_6.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val radioViewModel: RadioViewModel by viewModels()
    private val videoViewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        radioViewModel.initializePlayer(this)
        videoViewModel.initializePlayer(this)

        setupAudioControls()
        setupVideoControls()
    }

    private fun setupAudioControls() = with(binding) {
        binding.playPauseRadio.setOnClickListener {
            if (radioViewModel.isPlaying) {
                radioViewModel.pauseRadio()
                binding.playPauseRadio.text = "Pause Radio"
            } else {
                radioViewModel.playCurrentStation()
                binding.playPauseRadio.text = "Play Radio"
            }
        }

        binding.nextRadio.setOnClickListener { radioViewModel.nextStation() }

        binding.prevRadio.setOnClickListener { radioViewModel.prevStation() }

        binding.volumeControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val volume = progress / 100.0f
                    radioViewModel.setVolume(volume)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupVideoControls() = with(binding) {
        playVideo.setOnClickListener {
            if (videoViewModel.isPlaying) videoViewModel.pauseVideo()
            else videoViewModel.playVideo()
            videoPlayerView.player = videoViewModel.player
        }

        nextVideo.setOnClickListener { videoViewModel.nextVideo() }

        prevVideo.setOnClickListener { videoViewModel.previousVideo() }
    }

    override fun onPause() {
        super.onPause()
        radioViewModel.pauseRadio()
        videoViewModel.pauseVideo()
    }

    override fun onStop() {
        super.onStop()
        radioViewModel.releasePlayer()
        videoViewModel.releasePlayer()
    }
}

