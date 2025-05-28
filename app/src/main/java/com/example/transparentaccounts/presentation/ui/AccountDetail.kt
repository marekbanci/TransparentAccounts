package com.example.transparentaccounts.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.transparentaccounts.data.network.UiState
import com.example.transparentaccounts.domain.utils.Utils
import com.example.transparentaccounts.presentation.viewmodels.TransparentAccountDetailVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetail(
    navController: NavController,
    viewModel: TransparentAccountDetailVM = hiltViewModel()
) {
    val detailState by viewModel.account.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = detailState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = state.data.name,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                Spacer(modifier = Modifier.padding(top = 16.dp))

                                Text(
                                    text = "Account: ${state.data.accountNumber}/${state.data.bankCode}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Start
                                )

                                Spacer(modifier = Modifier.padding(top = 16.dp))

                                Text(
                                    text = "Iban: ${state.data.iban}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Start
                                )

                                Spacer(modifier = Modifier.padding(top = 16.dp))

                                Row(modifier = Modifier
                                    .fillMaxWidth()) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("From: ${Utils.formatDateToDisplay(state.data.transparencyFrom)}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("To: ${Utils.formatDateToDisplay(state.data.transparencyTo)}")
                                    }
                                }

                                Spacer(modifier = Modifier.padding(top = 16.dp))

                                Row(modifier = Modifier
                                    .fillMaxWidth()) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Last update: ${Utils.formatDateToDisplay(state.data.actualizationDate)}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Value: ${Utils.formatCurrency(state.data.balance, state.data.currency!!)}")
                                    }
                                }
                            }
                        }
                    }

                }

                is UiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error: ${state.message}", color = Color.Red)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { navController.popBackStack() }) {
                            Text("Back")
                        }
                    }
                }
            }
        }
    }
}