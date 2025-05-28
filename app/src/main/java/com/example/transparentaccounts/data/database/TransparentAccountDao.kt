package com.example.transparentaccounts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.transparentaccounts.domain.model.TransparentAccount

@Dao
interface TransparentAccountDao {

    @Query("SELECT * FROM transparent_accounts WHERE iban = :iban LIMIT 1")
    suspend fun getByIban(iban: String): TransparentAccount

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(accounts: List<TransparentAccount>)
}