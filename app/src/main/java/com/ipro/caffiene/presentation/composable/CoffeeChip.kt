package com.ipro.caffiene.presentation.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.Theme

@Composable
fun CoffeeChip(
    modifier: Modifier = Modifier,
    buttonSize: Dp = 48.dp,
    @DrawableRes iconRes: Int,
    contentDescription: String? = null,
    isDropShadow: Boolean = true,
    shadow: DropShadow = DropShadow(
        color = Theme.color.secondary.copy(alpha = 0.5f),
        blur = 16.dp,
        offsetY = 6.dp,),
    backgroundColor: Color = Theme.color.secondary,
    onClick: () -> Unit = {},
){
    Row(
        modifier = modifier
            .then(
                if (isDropShadow) {
                    Modifier.dropShadow(
                        shape = shadow.shape,
                        color = shadow.color,
                        offsetX = shadow.offsetX,
                        offsetY = shadow.offsetY,
                        blur = shadow.blur
                    )
                } else Modifier
            )
            .size(buttonSize)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = contentDescription, tint = Color.Unspecified
        )
    }
}



@Composable
@Preview(showBackground = true,)
fun CoffeeChipPreview() {
    CoffeeChip(iconRes = R.drawable.ic_check,)
}