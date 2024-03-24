package com.example.midtermproject.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class DashboardViewModel : ViewModel() {
    private var timer: Timer? = null
    private val _time = MutableLiveData<Long>() // might change to long

    val time: LiveData<Long>
        get() = _time

    init {
        _time.value = 0
    }

    fun startTimer() {
        timer = Timer().apply {
            scheduleAtFixedRate(0, 1000) {
                _time.postValue(_time.value!! + 1)
            }
        }
    }

    fun stopPauseTimer() {
        timer?.cancel()
    }

    fun resetTimer() {
        stopPauseTimer()
        _time.postValue(0)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}


