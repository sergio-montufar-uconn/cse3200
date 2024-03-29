package com.example.radiolab.model

class RadioStationModel {
    var urls = listOf("https://kptz.streamguys1.com/live-mp3",
        "http://stream.jam.fm/jamfm-bl/mp3-192/",
        "http://crystalout.surfernetwork.com:8001/WDRC-AM_MP3",
        "http://samcloud.spacial.com/api/listen",
        "http://hydra.cdnstream.com/1536_128")


    private var currentUrl = -1
    private fun getNextUrl() : String {
        currentUrl = (currentUrl+1) % urls.size
        return urls[currentUrl]
    }
}

