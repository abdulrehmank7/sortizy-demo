package com.arkapp.sortizydemo.utils

import com.arkapp.sortizydemo.data.models.Appointment
import java.util.*
import kotlin.collections.ArrayList

fun sortedAppointment(): List<Appointment> {
    return getMockAppointments().sortedBy { it.time.time }
}

fun sortedUpcomingAppointment(): List<Appointment> {
    return getMockUpcomingAppointments().sortedBy { it.time.time }
}

fun getMockAppointments(): ArrayList<Appointment> {
    val mockAppointment = ArrayList<Appointment>()

    val currentCal = Calendar.getInstance()

    currentCal.add(Calendar.MINUTE, 100)
    val date1 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "1",
            "Abdul Rehman",
            "7073342591",
            "36, Bhato ka was, Ratlam(M.P.)",
            date1
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    val date2 = Date(currentCal.timeInMillis)
    mockAppointment.add(
        Appointment(
            "2",
            "Akash Verma",
            "7073342591",
            "21, Manak Chowk, Ratlam(M.P.)",
            date2
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    val date3 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "3",
            "Deepak Kumar",
            "7073342591",
            "97, Chandni Chowk, Ratlam(M.P.)",
            date3
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    val date4 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "4",
            "Narayan Sundar Jeff",
            "7073342591",
            "971, Chandni Chowk, Ratlam(M.P.)",
            date4
        )
    )

    return mockAppointment
}

fun getMockUpcomingAppointments(): ArrayList<Appointment> {
    val mockAppointment = ArrayList<Appointment>()

    val currentCal = Calendar.getInstance()

    currentCal.add(Calendar.MINUTE, 100)
    currentCal.set(Calendar.DAY_OF_WEEK, 1)

    val date1 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "1",
            "Abdul Rehman",
            "7073342591",
            "36, Bhato ka was, Ratlam(M.P.)",
            date1
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    currentCal.set(Calendar.DAY_OF_WEEK, 2)

    val date2 = Date(currentCal.timeInMillis)
    mockAppointment.add(
        Appointment(
            "2",
            "Akash Verma",
            "7073342591",
            "21, Manak Chowk, Ratlam(M.P.)",
            date2
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    currentCal.set(Calendar.DAY_OF_WEEK, 2)

    val date3 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "3",
            "Deepak Kumar",
            "7073342591",
            "97, Chandni Chowk, Ratlam(M.P.)",
            date3
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    currentCal.set(Calendar.DAY_OF_WEEK, 5)

    val date4 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "4",
            "Narayan Sundar Jeff",
            "7073342591",
            "971, Chandni Chowk, Ratlam(M.P.)",
            date4
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    currentCal.set(Calendar.DAY_OF_WEEK, 4)

    val date5 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "5",
            "Michel",
            "7073342591",
            "12, Freeganj Road, Ratlam(M.P.)",
            date5
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    currentCal.set(Calendar.DAY_OF_WEEK, 5)

    val date6 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "6",
            "Harsh Pandey",
            "7073342591",
            "971, Chandni Chowk, Ratlam(M.P.)",
            date6
        )
    )

    currentCal.add(Calendar.MINUTE, 70)
    currentCal.set(Calendar.DAY_OF_WEEK, 2)

    val date7 = Date(currentCal.timeInMillis)

    mockAppointment.add(
        Appointment(
            "7",
            "Saniya Mirza",
            "7073342591",
            "101, Baker Street, Ratlam(M.P.)",
            date7
        )
    )
    return mockAppointment
}