package com.example.rumin.utils

import com.example.rumin.R

data class Navbar(
    val title: String,
    val icon: Int,
)

val navbarItems = listOf(
    Navbar(title = "Home", icon = R.drawable.home),
    Navbar(title = "Reminders", icon = R.drawable.reminder),
    Navbar(title = "Profile", icon = R.drawable.profile)
)