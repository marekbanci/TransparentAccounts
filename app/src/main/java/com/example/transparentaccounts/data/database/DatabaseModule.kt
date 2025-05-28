package com.example.transparentaccounts.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "accounts.db"
            ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    fun provideTransparentAccountDao(db: AppDatabase): TransparentAccountDao {
        return db.transparentAccountDao()
    }
}