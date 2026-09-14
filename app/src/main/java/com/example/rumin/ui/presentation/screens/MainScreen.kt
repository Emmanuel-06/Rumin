package com.example.rumin.ui.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rumin.ui.presentation.viewmodel.VerseViewModel
import com.example.rumin.utils.navbarItems
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey100
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Yellow100
import com.example.rumin.ui.theme.Yellow200


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    verseViewModel: VerseViewModel,
) {
    var selected by remember { mutableStateOf(0) }

    var scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            when (selected) {
                0 -> HomeTopAppBar()
//                1 -> ReminderTopAppBar()
//                2 -> ProfileScreenTopAppBar()
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier,
                containerColor = Yellow100,
            ) {
                navbarItems.forEachIndexed { index, navbarItems ->
                    val isSelected = selected == index

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            selected = index
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected)
                                    ImageVector.vectorResource(id = navbarItems.selectedIcon)
                                else
                                    ImageVector.vectorResource(id = navbarItems.unselectedIcon),
                                contentDescription = "Nav bar icon",
                                tint = if (isSelected) Black else Grey400,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        ),
                        label = {
                            Text(
                                navbarItems.title,
                                fontWeight = FontWeight.Medium,
                                color = if (isSelected) Black else Grey400
                            )
                        },
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        },
        containerColor = Yellow100,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 21.dp)
                .verticalScroll(scrollState)
        ) {
            ScreenContainer(
                index = selected,
                viewModel = verseViewModel
            )
        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenContainer(
    index: Int,
    viewModel: VerseViewModel,
) {
    when (index) {
        0 -> Home(viewModel)
//        1 -> RemindersScreen()
//        2 -> ProfileScreen()
    }
}
