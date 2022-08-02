package com.example.test_global_indo_multimedia.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_global_indo_multimedia.models.Cart
import com.example.test_global_indo_multimedia.models.CartDAO

@Database(
    entities = [Cart::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun getCartDAO() : CartDAO

    companion object{
        @Volatile
        private var INSTANCE: RoomDataBase? = null

        fun getDatabase(mContext : Context): RoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    mContext.applicationContext,
                    RoomDataBase::class.java,
                    "cart_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}