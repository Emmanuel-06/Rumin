package com.example.rumin.ui.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.rumin.ui.presentation.viewmodel.RuminViewModel
import com.example.rumin.utils.navbarItems
import com.example.rumin.ui.theme.Black
import com.example.rumin.ui.theme.Grey200
import com.example.rumin.ui.theme.Grey400
import com.example.rumin.ui.theme.Yellow200


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController,
    ruminViewModel: RuminViewModel
) {
    var selected by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            when(selected){
                0 -> HomeScreenTopAppBar()
                1 -> ReminderTopAppBar()
            }
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .drawWithContent {
                        drawContent()
                        val strokeWidth = 1.dp.toPx()

                        drawLine(
                            color = Grey200,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = strokeWidth
                        )
                    },
                containerColor = Yellow200,
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
                                imageVector = ImageVector.vectorResource(id = navbarItems.icon),
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
                        modifier = Modifier
                    )
                }
            }
        },
        containerColor = Yellow200,
//        modifier = Modifier.statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            ScreenContainer(
                index = selected,
                viewModel = ruminViewModel
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenContainer(
    index: Int,
    viewModel: RuminViewModel
) {
    when(index){
        0 -> HomeScreen(viewModel)
        1 -> RemindersScreen()
        2 -> ProfileScreen()
    }
}
