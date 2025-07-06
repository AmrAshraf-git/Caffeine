package com.ipro.caffiene.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.ipro.caffiene.designsystem.textstyle.defaultTextStyle
import com.ipro.caffiene.designsystem.color.caffeineDarkColors
import com.ipro.caffiene.designsystem.color.caffeineLightColors
import com.ipro.caffiene.designsystem.color.localCaffeineColors
import com.ipro.caffiene.designsystem.textstyle.localCaffeineTextStyle


@Composable
fun CaffeineTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) caffeineDarkColors else caffeineLightColors

    val materialColors = if (isDarkTheme) {
        darkColorScheme(
            primary = colors.primary,
            secondary = colors.secondary,
            background = colors.surface,
            surface = colors.surface,
            onPrimary = colors.textColors.onPrimary,
            onSecondary = colors.onSecondary,
        )
    } else {
        lightColorScheme(
            primary = colors.primary,
            secondary = colors.secondary,
            background = colors.surface,
            surface = colors.surface,
            onPrimary = colors.textColors.onPrimary,
            onSecondary = colors.onSecondary,
        )
    }

    CompositionLocalProvider(
        localCaffeineColors provides colors,
        localCaffeineTextStyle provides defaultTextStyle
    ) {
        MaterialTheme(
            colorScheme = materialColors
        ) {
            content()
        }
        //content()
    }
}