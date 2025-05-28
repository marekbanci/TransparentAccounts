package com.example.transparentaccounts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.transparentaccounts.presentation.ui.AccountDetail
import com.example.transparentaccounts.presentation.ui.ListUI

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { ListUI(navController) }
        composable(
            route = "detail/{iban}",
            arguments = listOf(navArgument("iban") { type = NavType.StringType })
        ) { backStackEntry ->
            val iban = backStackEntry.arguments?.getString("iban")
            if (iban != null) {
                AccountDetail(navController)
            }
        }
    }
}