package com.example.rumin.utils

import com.example.rumin.R

data class Navbar(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
)

val navbarItems = listOf(
    Navbar(title = "Home", selectedIcon = R.drawable.home_fill, unselectedIcon = R.drawable.home_outline),
    Navbar(title = "Reminders", selectedIcon = R.drawable.clock_filled, unselectedIcon = R.drawable.clock_outline),
    Navbar(title = "Profile", selectedIcon = R.drawable.profile_filled, unselectedIcon = R.drawable.profile_outline)
)