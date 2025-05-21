package br.com.rodrigo.bemestarapp.data.local

import androidx.room.*

@Dao
interface CheckDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checkIn: CheckEntity)

    @Query("SELECT * FROM checkin ORDER BY date DESC LIMIT 7")
    suspend fun getWeeklyCheckIns(): List<CheckEntity>

    @Query("SELECT * FROM checkin ORDER BY date DESC LIMIT 3")
    suspend fun getLastThreeCheckIns(): List<CheckEntity>
}
