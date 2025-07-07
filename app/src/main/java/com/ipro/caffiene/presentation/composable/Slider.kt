package com.ipro.caffiene.presentation.composable

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import com.ipro.caffiene.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ipro.caffiene.designsystem.theme.Theme

@Composable
fun SizePicker(
    modifier: Modifier = Modifier,
    initialSizeIndex: Int = 0,
    sizeOptions: List<SizeOption>,
    onSizeSelected: (index: Int) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(initialSizeIndex.coerceIn(0, sizeOptions.size - 1)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(Theme.color.iconBackground, RoundedCornerShape(100.dp))
            .padding( 8.dp)
            .wrapContentWidth()
    ) {
        sizeOptions.forEachIndexed { index, option ->
            val isSelected = selectedIndex == index
            val circleOpacity by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0f,
                animationSpec = tween(durationMillis = 600)
            )
            val elevation by animateDpAsState(
                targetValue = if (isSelected) 3.dp else 0.dp,
                animationSpec = tween(durationMillis = 600)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = CircleShape)
                    .clickable {
                        selectedIndex = index
                        onSizeSelected(index)
                    }
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .shadow(elevation = elevation, shape = CircleShape)
                            .background(
                                color = Theme.color.secondary.copy(alpha = circleOpacity),
                                shape = CircleShape
                            )
                    )
                }
                when (option) {
                    is SizeOption.TextOption -> {
                        Text(
                            text = option.text,
                            style = Theme.textStyle.title.large,
                            color = if (isSelected) Theme.color.textColors.onPrimary else Theme.color.textColors.title,
                        )
                    }
                    is SizeOption.IconOption -> {
                        Icon(
                            painter = painterResource(option.iconResource),
                            contentDescription = null,
                            tint = if (isSelected) Theme.color.textColors.onPrimary else Theme.color.textColors.title,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
            }
        }
    }
}

sealed interface SizeOption {
    data class TextOption(val text: String) : SizeOption
    data class IconOption(val iconResource: Int) : SizeOption
}



@Composable
@Preview(showBackground = true)
fun MySliderPreview() {
    val textOptions = listOf(
        SizeOption.TextOption("S"),
        SizeOption.TextOption("M"),
        SizeOption.TextOption("L")
    )
    val iconOptions = listOf(
        SizeOption.IconOption ( R.drawable.ic_coffee_beans),
        SizeOption.IconOption ( R.drawable.ic_coffee_beans),
        SizeOption.IconOption ( R.drawable.ic_coffee_beans),
    )
    // Preview with text options
//    SizePicker(
//        initialSizeIndex = 1,
//        sizeOptions = textOptions
//    ) { selectedSize ->
//        //println("Selected size: $selectedSize")
//    }
    // Preview with icon options (uncomment to test)
     SizePicker(
         initialSizeIndex = 0,
         sizeOptions = iconOptions
     ) { selectedSize ->
         //println("Selected size: $selectedSize")
     }
}