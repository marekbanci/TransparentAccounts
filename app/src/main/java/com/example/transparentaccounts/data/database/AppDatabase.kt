package com.example.transparentaccounts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.transparentaccounts.domain.model.TransparentAccount

@Database(
    entities = [TransparentAccount::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transparentAccountDao(): TransparentAccountDao
}