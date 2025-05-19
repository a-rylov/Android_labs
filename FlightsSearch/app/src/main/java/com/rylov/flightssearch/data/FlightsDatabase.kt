package com.rylov.flightssearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rylov.flightssearch.data.dao.AirportDao
import com.rylov.flightssearch.data.dao.FavoriteDao
import com.rylov.flightssearch.data.model.Airport
import com.rylov.flightssearch.data.model.Favorite

@Database(
    entities = [Airport::class, Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class FlightsDatabase : RoomDatabase() {
    abstract fun airportDao(): AirportDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FlightsDatabase? = null

        fun getDatabase(context: Context): FlightsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = INSTANCE
                if (instance != null) {
                    return instance
                }

                val createdInstance = Room.databaseBuilder(
                    context.applicationContext,
                    FlightsDatabase::class.java,
                    "flight_search.db"
                )
                    .createFromAsset("flight_search.db")
                    .build()

                INSTANCE = createdInstance
                return createdInstance
            }
        }
    }
}
