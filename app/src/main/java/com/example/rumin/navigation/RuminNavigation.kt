package com.example.rumin.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rumin.ui.presentation.screens.MainScreen
import com.example.rumin.ui.presentation.viewmodel.VerseViewModel
import com.example.rumin.utils.Screens

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RuminNavigation() {

    val navController = rememberNavController()

    val viewModel: VerseViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.MAIN.name
    ) {
        composable(
            route = Screens.MAIN.name
        ) {
            MainScreen(
                navController = navController,
                verseViewModel = viewModel
            )
        }
    }
}