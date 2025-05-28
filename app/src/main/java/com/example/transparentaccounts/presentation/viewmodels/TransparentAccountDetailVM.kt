package com.example.transparentaccounts.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transparentaccounts.data.network.UiState
import com.example.transparentaccounts.domain.model.TransparentAccount
import com.example.transparentaccounts.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransparentAccountDetailVM @Inject constructor(
    private val dao: DatabaseUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _account =  MutableStateFlow<UiState<TransparentAccount>>(UiState.Loading)
    val account: StateFlow<UiState<TransparentAccount>> =_account

    init {
        val iban: String = savedStateHandle["iban"] ?: ""
        getTransparentAccount(iban)
    }

    private fun getTransparentAccount(iban: String) {
        viewModelScope.launch {
            val data = dao.getAccountByIban(iban)
            _account.value = UiState.Success(data)
        }
    }
}