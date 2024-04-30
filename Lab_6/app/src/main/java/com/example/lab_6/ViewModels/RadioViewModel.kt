package com.example.lab_6.ViewModels


import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

class RadioViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var reference: WeakReference<Context>
    private var currentUrl = 0
    private var radioUrls = listOf(
        "https://kptz.streamguys1.com/live-mp3",
        "http://stream.jam.fm/jamfm-bl/mp3-192/",
        "http://crystalout.surfernetwork.com:8001/WDRC-AM_MP3",
        "http://stream.rtlradio.de/rnb/mp3-192/",
        "http://hydra.cdnstream.com/1536_128"
    )

    fun initializePlayer(context: Context) {
        reference = WeakReference(context)
        mediaPlayer = MediaPlayer().apply {
            setOnPreparedListener {
                start()
            }
            setOnErrorListener { _, bing, bong ->
                true
            }
        }
        playCurrentStation()
    }

    val isPlaying: Boolean
        get() = mediaPlayer?.isPlaying ?: false

    fun playCurrentStation() {
        mediaPlayer?.apply {
            reset()
            setDataSource(reference.get()!!, android.net.Uri.parse(radioUrls[currentUrl]))
            prepareAsync()
        }
    }

    fun nextStation() {
        if (currentUrl < radioUrls.size - 1) {
            currentUrl++
            playCurrentStation()
        }
    }

    fun prevStation() {
        if (currentUrl > 0) {
            currentUrl--
            playCurrentStation()
        }
    }

    fun setVolume(volume: Float) {
        mediaPlayer?.setVolume(volume, volume)
    }

    fun pauseRadio() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }

}