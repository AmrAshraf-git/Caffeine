package com.ipro.caffiene.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.CaffeineTheme
import com.ipro.caffiene.designsystem.theme.Theme


@Composable
fun Header(modifier: Modifier = Modifier, onSuffixClick: () -> Unit = {}) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "Avatar",
            modifier = Modifier.size(48.dp).clip(CircleShape),
        )
        IconButton(onClick = onSuffixClick,
            Modifier.background(color = Theme.color.iconBackground, CircleShape)) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Theme.color.textColors.onPrimaryBody)
        }
    }
}


@Preview(name = "Dark Theme", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeaderDarkModePreview() {
    CaffeineTheme {
        Header()
    }
}
@Preview(name = "Light Theme", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HeaderLightModePreview() {
    CaffeineTheme {
        Header()
    }
}
