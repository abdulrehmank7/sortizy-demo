package com.arkapp.sortizydemo.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdul Rehman on 19-04-2021.
 * Contact email - abdulrehman0796@gmail.com
 */

@Entity(tableName = "USER_LOGIN")
data class UserLogin(
    @PrimaryKey(autoGenerate = true)
    val uid: Int?,
    @ColumnInfo(name = "userName")
    val userName: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "password")
    val password: String?
)