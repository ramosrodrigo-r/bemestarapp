package br.com.rodrigo.bemestarapp.data.local

import androidx.room.*

@Dao
interface CheckInDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checkIn: CheckInEntity)

    @Query("SELECT * FROM checkin ORDER BY date DESC LIMIT 7")
    suspend fun getWeeklyCheckIns(): List<CheckInEntity>

    @Query("SELECT * FROM checkin ORDER BY date DESC LIMIT 3")
    suspend fun getLastThreeCheckIns(): List<CheckInEntity>
}
