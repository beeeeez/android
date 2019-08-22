package com.example.inclassweek6

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase




@Database(entities=[Account::class] ,version=1)
        abstract class AppDB: RoomDatabase() {
            abstract fun BankAccountDao(): BankAccountDao
    companion object {
        var INSTANCE: AppDB? = null

        fun getAppDataBase(context: Context): AppDB? {
            if (INSTANCE == null){
                synchronized(AppDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDB::class.java, "myDB")
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
