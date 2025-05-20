package br.com.rodrigo.bemestarapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkin")
data class CheckInEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val mood: Int,
    val note: String?,
    val motivation: Int,
    val focus: Int,
    val support: Int
)
