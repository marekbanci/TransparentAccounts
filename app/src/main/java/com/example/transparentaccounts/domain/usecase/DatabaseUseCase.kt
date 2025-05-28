package com.example.transparentaccounts.domain.usecase

import com.example.transparentaccounts.data.database.TransparentAccountDao
import com.example.transparentaccounts.domain.model.TransparentAccount
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val dao: TransparentAccountDao
) {
    suspend fun insertAccounts(accounts: List<TransparentAccount>) {
        dao.insertAll(accounts)
    }

    suspend fun getAccountByIban(iban: String): TransparentAccount {
        return dao.getByIban(iban)
    }
}