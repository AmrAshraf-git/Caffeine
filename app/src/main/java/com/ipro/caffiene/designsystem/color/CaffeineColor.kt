package com.ipro.caffiene.designsystem.color

import androidx.compose.ui.graphics.Color

data class CaffeineColor(
    val primary: Color,
    val secondary: Color,
    val primaryVariant: Color,
    val disable: Color,
    val surface: Color,
    val stroke: Color,
    val iconBackground: Color,
    val textColors: TextColors,
)

data class TextColors(
    val headline: Color,
    val title: Color,
    val body: Color,
    val hint: Color,
    val label: Color,
    val onPrimary: Color,
    val onPrimaryBody: Color,
    val onPrimaryHint: Color,
    )
