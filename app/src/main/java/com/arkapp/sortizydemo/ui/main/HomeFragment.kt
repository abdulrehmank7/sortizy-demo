package com.arkapp.sortizydemo.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.arkapp.sortizydemo.R
import com.arkapp.sortizydemo.data.database.AppDatabase
import com.arkapp.sortizydemo.data.preferences.ENTERED_USER_EMAIL
import com.arkapp.sortizydemo.data.preferences.ENTERED_USER_NAME
import com.arkapp.sortizydemo.data.repository.PrefRepository
import com.arkapp.sortizydemo.utils.hide
import com.arkapp.sortizydemo.utils.initVerticalAdapter
import com.arkapp.sortizydemo.utils.show
import com.arkapp.sortizydemo.utils.sortedAppointment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val preRepository by lazy { PrefRepository(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress.show()
        showAppointmentList()
        addUserName()
    }

    private fun showAppointmentList() {
        val adapter = AppointmentListAdapter(
            sortedAppointment(),
            preRepository,
            findNavController()
        )
        appointmentRv.initVerticalAdapter(adapter, true)
        progress.hide()
    }

    private fun addUserName() {
        if (ENTERED_USER_NAME.isNotEmpty() || ENTERED_USER_EMAIL.isNotEmpty()) {
            lifecycleScope.launch(Dispatchers.Main) {
                val userLoginDao = AppDatabase.getDatabase(requireContext()).userLoginDao()

                if (ENTERED_USER_NAME.isNotEmpty()) {
                    val userData = userLoginDao.checkLoggedInUser(ENTERED_USER_NAME)
                    preRepository.setCurrentLoginUser(userData[0])
                } else {
                    val userData = userLoginDao.checkLoggedInUserEmail(ENTERED_USER_EMAIL)
                    preRepository.setCurrentLoginUser(userData[0])
                }
            }
        }
    }

}
