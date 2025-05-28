package com.example.transparentaccounts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transparent_accounts")
data class TransparentAccount(
    @PrimaryKey val iban: String,
    val accountNumber: String,
    val bankCode: String,
    val transparencyFrom: String,
    val transparencyTo: String,
    val publicationTo: String,
    val actualizationDate: String,
    val balance: Double,
    val currency: String? = "",
    val name: String
)