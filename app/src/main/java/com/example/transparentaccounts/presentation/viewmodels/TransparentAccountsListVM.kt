package com.example.transparentaccounts.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transparentaccounts.domain.model.TransparentAccount
import com.example.transparentaccounts.domain.usecase.AccountsUseCase
import com.example.transparentaccounts.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransparentAccountsListVM @Inject constructor(
    private val useCase: AccountsUseCase,
    private val dao: DatabaseUseCase
) :
    ViewModel() {

    private val _accounts = MutableStateFlow<List<TransparentAccount>>(emptyList())
    val accounts: StateFlow<List<TransparentAccount>> = _accounts

    private var nextPage = 0;
    private var limit = 50

    init {
        loadNextPage()
    }

    fun getNextPage() {
        loadNextPage()
    }

    private fun loadNextPage() {
        viewModelScope.launch {
            val resp = useCase.fetchAccounts(nextPage, limit)
            nextPage = resp.nextPage
            limit = resp.pageSize
            _accounts.value = _accounts.value.plus(resp.accounts)
            dao.insertAccounts(resp.accounts)
        }
    }
}