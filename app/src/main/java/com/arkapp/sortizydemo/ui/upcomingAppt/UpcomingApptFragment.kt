package com.arkapp.sortizydemo.ui.upcomingAppt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkapp.sortizydemo.R
import com.arkapp.sortizydemo.utils.hide
import com.arkapp.sortizydemo.utils.initVerticalAdapter
import com.arkapp.sortizydemo.utils.sortedUpcomingAppointment
import kotlinx.android.synthetic.main.fragment_home.progress
import kotlinx.android.synthetic.main.fragment_upcoming_appt.*

class UpcomingApptFragment : Fragment(R.layout.fragment_upcoming_appt) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showUpcomingAppointmentList()
    }

    private fun showUpcomingAppointmentList() {
        val adapter = UpcomingAppointmentListAdapter(
            sortedUpcomingAppointment()
        )
        upcomingAppointmentRv.initVerticalAdapter(adapter, true)
        progress.hide()
    }
}