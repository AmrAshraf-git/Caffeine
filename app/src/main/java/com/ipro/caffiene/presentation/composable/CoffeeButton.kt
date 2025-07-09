package com.ipro.caffiene.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.CaffeineTheme
import com.ipro.caffiene.designsystem.theme.Theme

@Composable
fun CoffeeButton(text: String, suffixIcon: Int, onClick: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(24.dp))
            .clip(shape = RoundedCornerShape(24.dp))
            .background(Theme.color.onSecondary)
            .clickable { onClick() }
            .padding(horizontal = 32.dp, vertical = 18.dp)
    ) {
        Text(text,
            style = Theme.textStyle.label.large,
            color = Theme.color.textColors.onPrimary)
        Icon(painter = painterResource(id = suffixIcon), contentDescription = null,
            tint =Theme.color.textColors.onPrimary)
    }
}



@Preview(heightDp = 300,
    name = "Dark Theme", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ButtonDarkModePreview() {
    CaffeineTheme {
        CoffeeButton(text = "bring my coffee", suffixIcon = R.drawable.ic_coffee)
    }
}
@Preview(name = "Light Theme", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ButtonLightModePreview() {
    CaffeineTheme {
        CoffeeButton(text = "bring my coffee", suffixIcon = R.drawable.ic_coffee)
    }
}