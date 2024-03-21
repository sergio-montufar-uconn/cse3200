package com.example.midtermproject.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.midtermproject.databinding.FragmentDashboardBinding
import com.example.midtermproject.ui.notifications.NotificationsViewModel

class DashboardFragment : Fragment() {

    private lateinit var timerViewModel: NotificationsViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Instantiate the ViewModel scoped to the Fragment
        timerViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        timerViewModel.time.observe(viewLifecycleOwner) {
            binding.textDashboard.text = formatTime(it)
        }

        binding.startButton.setOnClickListener { timerViewModel.startTimer() }
        binding.pauseButton.setOnClickListener { timerViewModel.stopPauseTimer() }
        binding.resetButton.setOnClickListener { timerViewModel.resetTimer() }
    }

    private fun formatTime(seconds: Long): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, secs)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}