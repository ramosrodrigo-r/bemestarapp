package br.com.rodrigo.bemestarapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CheckEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checkInDao(): CheckDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "check_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}