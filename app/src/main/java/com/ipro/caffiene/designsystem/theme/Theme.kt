package com.ipro.caffiene.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.ipro.caffiene.designsystem.color.CaffeineColor
import com.ipro.caffiene.designsystem.color.localCaffeineColors
import com.ipro.caffiene.designsystem.textstyle.CaffeineTextStyle
import com.ipro.caffiene.designsystem.textstyle.localCaffeineTextStyle

object Theme {
    val color: CaffeineColor
        @Composable @ReadOnlyComposable get() = localCaffeineColors.current
    val textStyle: CaffeineTextStyle
        @Composable @ReadOnlyComposable get() = localCaffeineTextStyle.current
}