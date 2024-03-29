package com.example.radiolab

import androidx.appcompat.app.AppCompatActivity
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var buttonPlay: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonNext: Button
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var image: ImageView

    private var urls = listOf("https://kptz.streamguys1.com/live-mp3",
                              "http://stream.jam.fm/jamfm-bl/mp3-192/",
                              "http://crystalout.surfernetwork.com:8001/WDRC-AM_MP3",
                              "http://stream.rtlradio.de/rnb/mp3-192/",
                              "http://hydra.cdnstream.com/1536_128")
    private var currentUrl = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay = findViewById(R.id.buttonPlay)
        buttonPause = findViewById(R.id.buttonPause)
        buttonNext = findViewById(R.id.buttonNext)
        seekBar = findViewById(R.id.volumeBar)
        image = findViewById(R.id.stationImage)

        setUpRadio()

        buttonPlay.setOnClickListener {
            mediaPlayer.start()
            Toast.makeText(this, "Station has begun playing", Toast.LENGTH_LONG).show()
        }

        buttonPause.setOnClickListener {
            mediaPlayer.pause()
            Toast.makeText(this, "Station has stopped playing", Toast.LENGTH_LONG).show()
        }

        buttonNext.setOnClickListener {
            playRadioStation()
            image.setImageResource(setImageForStation(currentUrl))
            mediaPlayer.start()
            Toast.makeText(this, "Selected next station", Toast.LENGTH_LONG).show()
        }

        seekBar.progress = 50 // this is the initial volume
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress.toFloat() / 100
                mediaPlayer.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }

    private fun getNextUrl() : String {
        currentUrl = (currentUrl+1) % urls.size
        return urls[currentUrl]
    }

    private fun playRadioStation() {
        mediaPlayer?.apply {
            if (!isPlaying) {
                reset()
                val urlString = getNextUrl()
                setDataSource(urlString)
                prepareAsync()
            } else {
                mediaPlayer.pause()
            }
        }
    }

    private fun setImageForStation(index: Int): Int {
        return when (index) {
            0 -> R.drawable.kptz_radio
            1 -> R.drawable.jamfm_blacklabel
            2 -> R.drawable.talkofconnecticut
            3 -> R.drawable.music_icon
            4 -> R.drawable.tuvidafm
            else -> R.drawable.ic_launcher_background
        }
    }

    private fun setUpRadio() {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
    }


}