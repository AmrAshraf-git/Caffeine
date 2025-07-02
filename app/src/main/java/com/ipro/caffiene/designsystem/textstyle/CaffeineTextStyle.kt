package com.ipro.caffiene.designsystem.textstyle

import androidx.compose.ui.text.TextStyle

data class CaffeineTextStyle(
    val headline: SizedTextStyle,
    val title: SizedTextStyle,
    val body: SizedTextStyle,
    val label: SizedTextStyle,
)

data class SizedTextStyle(
    val large: TextStyle,
    val medium: TextStyle,
    val small: TextStyle,
)