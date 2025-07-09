package com.ipro.caffiene.presentation.composable

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.Theme

@Composable
fun CoffeeToggleButton(
    modifier: Modifier = Modifier,
    initialState: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    var checked by remember { mutableStateOf(initialState) }

    // Sizes
    val width = 76.dp
    val height = 40.dp
    val padding = 4.dp
    val iconBox = 32.dp

    // Animated colors & offsets
    val backgroundColor by animateColorAsState(
        targetValue = if (checked) Theme.color.secondary  else Theme.color.primaryVariant,
        label = "BackgroundColor"
    )
    val textColor by animateColorAsState(
        targetValue = if (checked) Theme.color.textColors.headline else  Theme.color.textColors.hint,
        label = "TextColor"
    )
    val iconOffset by animateDpAsState(
        targetValue = if (checked) padding else  (width - iconBox - padding),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "IconOffset"
    )
    val textOffset = if (checked) (width - iconBox - padding)-5.dp else  padding

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(100.dp))
            .background(backgroundColor)
            .clickable {
                checked = !checked
                onCheckedChange(checked)
            }
        ,
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = if (checked) "ON" else "OFF",
            color = textColor,
            style = Theme.textStyle.label.small.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset(x = textOffset)
                .align(Alignment.CenterStart)
                .padding(start = 12.dp, end = 12.dp)
        )

        Box(
            modifier = Modifier
                .offset(x = iconOffset)
                .size(iconBox)
                .clip(CircleShape)
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.coffee_cup_face),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier=Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeTogglePreview() {
    CoffeeToggleButton()
}
