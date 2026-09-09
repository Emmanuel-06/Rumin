package com.example.rumin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rumin.R


val sfRoundedFontFamily = FontFamily(
    Font(R.font.sf_pro_rounded, FontWeight.Normal),
    Font(R.font.sf_pro_rounded_medium, FontWeight.Medium),
    Font(R.font.sf_pro_rounded_semibold, FontWeight.SemiBold),
)


val CustomTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    bodySmall = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    displayMedium = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp,
    ),

    titleLarge = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 38.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    labelMedium = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    labelSmall = TextStyle(
        fontFamily = sfRoundedFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
    )
)

