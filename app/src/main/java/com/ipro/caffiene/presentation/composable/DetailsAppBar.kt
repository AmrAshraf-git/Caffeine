package com.ipro.caffiene.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.Theme

@Composable
fun DetailsAppBar(modifier: Modifier = Modifier,onBackClick: () -> Unit = {},) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
            IconButton(onClick = onBackClick,
                Modifier.background(color = Theme.color.iconBackground, CircleShape)) {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
                    contentDescription = "Add", tint = Theme.color.textColors.onPrimaryBody)
            }


        Text(text = "Macchiato",
            style = Theme.textStyle.title.large.copy(fontSize = 24.sp),
            color = Theme.color.textColors.onPrimaryBody
        )
    }
}

@Preview(showBackground = true,)
@Composable
fun DetailsAppBarPrev(modifier: Modifier = Modifier) {
    DetailsAppBar()
}