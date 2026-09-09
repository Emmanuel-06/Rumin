package com.example.rumin

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.work.WorkManager
import com.example.rumin.navigation.RuminNavigation
import com.example.rumin.ui.theme.RuminTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT)
        )
        setContent {
            RuminTheme {
                RuminNavigation()
            }
        }
    }
}
