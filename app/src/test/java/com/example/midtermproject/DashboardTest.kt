package com.example.midtermproject

import com.example.midtermproject.ui.dashboard.DashboardFragment
import com.example.midtermproject.ui.dashboard.DashboardViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DashboardTimerTest {

    @Test
    fun `Testing formatTime with 100 seconds`() {
        val fragment = DashboardFragment()
        val formattedTime = fragment.formatTime(100)
        assertEquals("00:01:40", formattedTime)
    }

    @Test
    fun `Testing formatTime with 1000 seconds`() {
        val fragment = DashboardFragment()
        val formattedTime = fragment.formatTime(1000)
        assertEquals("00:16:40", formattedTime)
    }

    @Test
    fun `Testing formatTime with 9999`() {
        val fragment = DashboardFragment()
        val formattedTime = fragment.formatTime(9999)
        assertEquals("02:46:39", formattedTime)
    }

    @Test
    fun `Testing formatTime with 3000`() {
        val fragment = DashboardFragment()
        val formattedTime = fragment.formatTime(3000)
        assertEquals("00:50:00", formattedTime)
    }

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        viewModel = DashboardViewModel()
    }

    @Test
    fun testTimerFunctionality() {
        viewModel.startTimer()

        Thread.sleep(100)
        assertEquals(1, viewModel.time.value?.toInt())

        viewModel.stopPauseTimer()
        Thread.sleep(1000)

        assertEquals(1, viewModel.time.value?.toInt())

        viewModel.resetTimer()
        assertEquals(0, viewModel.time.value?.toInt())
    }
}
