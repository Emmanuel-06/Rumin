package com.example.rumin.data

import com.example.rumin.R
import com.example.rumin.utils.Screens

data class Navbar(
    val title: String,
    val icon: Int,
    val route: String
)

val navbarItems = listOf(
    Navbar(title = "Home", icon = R.drawable.home, route = Screens.HOME.name),
    Navbar(title = "Verses", icon = R.drawable.verses, route = Screens.VERSES.name),
    Navbar(title = "Profile", icon = R.drawable.profile, route = Screens.PROFILE.name),
)