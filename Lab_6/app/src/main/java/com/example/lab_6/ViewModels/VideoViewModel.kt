package com.example.lab_6.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class VideoViewModel : ViewModel() {
    private var videoPlayer: ExoPlayer? = null
    private var currIndex = 0
    private var videoUrls = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")

    val isPlaying: Boolean
        get() = videoPlayer?.isPlaying ?: false

    val player: ExoPlayer?
        get() = videoPlayer

    private fun setMediaSource(index: Int) {
        val mediaItem = MediaItem.fromUri(videoUrls[index])
        videoPlayer?.setMediaItem(mediaItem)
        videoPlayer?.prepare()
        videoPlayer?.playWhenReady = false
    }

    fun initializePlayer(context: Context) {
        videoPlayer = ExoPlayer.Builder(context).build()
        setMediaSource(currIndex)
    }

    fun playVideo() {
        videoPlayer?.play()
    }

    fun pauseVideo() {
        videoPlayer?.pause()
    }

    fun nextVideo() {
        if (currIndex < videoUrls.size - 1) {
            currIndex++
            setMediaSource(currIndex)
        }
    }

    fun previousVideo() {
        if (currIndex > 0) {
            currIndex--
            setMediaSource(currIndex)
        }
    }

    fun releasePlayer() {
        videoPlayer?.release()
        videoPlayer = null
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }


}