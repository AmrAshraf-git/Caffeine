package com.ipro.caffiene.designsystem.textstyle

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ipro.caffiene.R


val sniglet = FontFamily(
    Font(R.font.sniglet_regular, weight = FontWeight.Normal),
    Font(R.font.sniglet_extra_bold, weight = FontWeight.ExtraBold),
)

val urbanist = FontFamily(
    Font(R.font.urbanist_regular, weight = FontWeight.Normal),
    Font(R.font.urbanist_medium, weight = FontWeight.Medium),
    Font(R.font.urbanist_bold, weight = FontWeight.Bold),
    Font(R.font.urbanist_semi_bold, weight = FontWeight.SemiBold),
)



val localCaffeineTextStyle = staticCompositionLocalOf { defaultTextStyle }


val defaultTextStyle: CaffeineTextStyle = CaffeineTextStyle(
    headline = SizedTextStyle(
        large = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = sniglet,
            lineHeight = 42.sp,

            ), medium = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = sniglet,
            lineHeight = 36.sp,
        ), small = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = sniglet,
            lineHeight = 30.sp,
        )
    ), title = SizedTextStyle(
        large = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = sniglet,
            lineHeight = 30.sp,
        ), medium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = sniglet,
            lineHeight = 28.sp,
        ), small = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = sniglet,
            lineHeight = 24.sp,
        )
    ), body = SizedTextStyle(
        large = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = sniglet,
            lineHeight = 28.sp,
        ), medium = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = sniglet,
            lineHeight = 24.sp,
        ), small = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = sniglet,
            lineHeight = 22.sp,
        )
    ), label = SizedTextStyle(
        large = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = sniglet,
            lineHeight = 24.sp,
        ), medium = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = sniglet,
            lineHeight = 22.sp,
        ), small = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = sniglet,
            lineHeight = 16.sp,
        )
    )
)