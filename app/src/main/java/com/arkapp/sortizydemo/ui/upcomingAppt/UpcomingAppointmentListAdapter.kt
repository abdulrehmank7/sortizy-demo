package com.arkapp.sortizydemo.ui.upcomingAppt

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arkapp.sortizydemo.R
import com.arkapp.sortizydemo.data.models.Appointment
import com.arkapp.sortizydemo.utils.*
import com.arkapp.sortizydemo.utils.viewHolders.UpcomingAppointmentViewHolder

/**
 * Created by Abdul Rehman on 19-04-2021.
 * Contact email - abdulrehman0796@gmail.com
 */

class UpcomingAppointmentListAdapter(
    private val appointment: List<Appointment>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UpcomingAppointmentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_upcoming_appointment,
                parent,
                false
            )
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as UpcomingAppointmentViewHolder).viewBinding

        val data = appointment[position]
        binding.personName.text = data.name
        binding.personAddress.text = data.address
        binding.timeTv.text = data.time.getFormattedDateTime()
        binding.dateTv.text = data.time.getFormattedDay()
        binding.monthTv.text = data.time.getFormattedMonth()

        binding.phoneBtn.setOnClickListener {
            it.context.showAlertDialog(
                it.context.getString(R.string.are_you_sure),
                "Call ${data.name}",
                it.context.getString(R.string.call),
                it.context.getString(R.string.cancel)
            ) { dialog, which ->
                binding.phoneBtn.context.makeCall(data.phone)
            }
        }

        binding.navigateBtn.setOnClickListener {
            it.context.showAlertDialog(
                it.context.getString(R.string.are_you_sure),
                it.context.getString(R.string.navigate_customer),
                it.context.getString(R.string.navigate),
                it.context.getString(R.string.cancel)
            ) { dialog, which ->
                it.context.toast(it.context.getString(R.string.not_implemented))
            }
        }
    }


    override fun getItemCount() = appointment.size

    override fun getItemId(position: Int): Long {
        return appointment[position].hashCode().toLong()
    }

}