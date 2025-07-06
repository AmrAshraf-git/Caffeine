package com.ipro.caffiene.designsystem.color

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val localCaffeineColors = staticCompositionLocalOf { caffeineLightColors }

val caffeineLightColors=CaffeineColor(
    primary = Color(0xFF291710),
    secondary = Color(0xFF7C351B),
    primaryVariant = Color(0xFFFFEEE7),
    onSecondary = Color(0xFF1F1F1F),
    iconBackground = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    stroke = Color(0xFF030004),
    disable = Color(0xFF292828),

    textColors = TextColors(
        headline = Color(0xFFB3B3B3),
        title = Color(0xFF3B3B3B),
        body = Color(0xFF1F1F1F),
        hint= Color(0x991F1F1F),
        label = Color(0x99000000),
        onPrimary = Color(0xDEFFFFFF),
        onPrimaryBody = Color(0xDE1F1F1F),
        onPrimaryHint = Color(0xCC1F1F1F),
    ),
)