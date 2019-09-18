package com.example.androidfinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase




@Database(entities=[Team::class] ,version=1)
abstract class AppDB: RoomDatabase() {
    abstract fun TeamDAO(): TeamDAO
    companion object {
        var INSTANCE: AppDB? = null

        fun getAppDataBase(context: Context): AppDB? {
            if (INSTANCE == null){
                synchronized(AppDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDB::class.java, "myDB2")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
