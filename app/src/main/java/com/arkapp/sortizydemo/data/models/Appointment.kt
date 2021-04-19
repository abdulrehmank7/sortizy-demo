package com.arkapp.sortizydemo.data.models

import java.util.*


/**
 * Created by Abdul Rehman on 19-04-2021.
 * Contact email - abdulrehman0796@gmail.com
 */
data class Appointment(
    val id: String,
    val name: String,
    val phone: String,
    val address: String,
    val time: Date
)