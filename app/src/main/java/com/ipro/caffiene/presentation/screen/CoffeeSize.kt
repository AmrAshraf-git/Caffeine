package com.ipro.caffiene.presentation.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.Theme
import com.ipro.caffiene.presentation.composable.DetailsAppBar
import com.ipro.caffiene.presentation.composable.CoffeeButton
import com.ipro.caffiene.presentation.composable.SizeOption
import com.ipro.caffiene.presentation.composable.CoffeeSlider
import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay

@Composable
fun CoffeeSizeScreen(
    modifier: Modifier = Modifier,
    coffeeName: String = "Coffee Type",
    onContinueClick: () -> Unit = {}
    ) {

    CoffeeSizeContent(
        modifier=modifier,
        onContinueClick = onContinueClick,
        coffeeName = coffeeName
        )
}

@Composable
fun CoffeeSizeContent(modifier: Modifier = Modifier,
                      onContinueClick: () -> Unit = {},
                      coffeeName: String = "Coffee Type"
    ) {
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
    val sizeToRes = listOf(
        R.drawable.cup_l to "150 Ml",
        R.drawable.cup_l to "200 Ml",
        R.drawable.cup_l to "240 Ml"
    )

    var selectedSizeIdx by remember { mutableIntStateOf(1) }
    var selectedRostIdx by remember { mutableIntStateOf(0) }

    val targetScale = when(selectedSizeIdx) {
        0 -> 0.8f
        1 -> 1f
        2 -> 1.2f
        else -> 1f
    }
    val animatedScale by animateFloatAsState(
        targetValue = targetScale,
        animationSpec = tween(durationMillis = 400)
    )

    var animationToken by remember { mutableStateOf<Int?>(null) }



    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding()
            .padding(horizontal = 16.dp,),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedAppBar {
            DetailsAppBar(iconRes = R.drawable.ic_arrow_left, title = coffeeName)
        }

        Box(
            modifier = modifier
                .height(340.dp)
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            if (animationToken != null) {
                AnimatedCoffeeBeans(
                    token=animationToken!!,
                    targetCupCenterY = 120.dp,
                    beanRes = R.drawable.coffee_beans_l,
                )
            }

            Image(
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 48.dp)
                    .height(244.dp)
                    .width(200.dp)
                    .align(Alignment.TopCenter)
                    .scale(animatedScale),
                painter = painterResource(sizeToRes[selectedSizeIdx].first),
                contentDescription = null
            )

            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(64.dp),
                painter = painterResource(R.drawable.ic_the_chance),
                contentDescription = null
            )

            Text(
                text = sizeToRes[selectedSizeIdx].second,
                style = Theme.textStyle.label.medium,
                color = Theme.color.textColors.label,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(160.dp)) {
            CoffeeSlider(sizeOptions = textOptions,
                initialSizeIndex = selectedSizeIdx,
                onSizeSelected = {
                        idx -> selectedSizeIdx = idx
                })

            Spacer(modifier = Modifier.height(16.dp))

            CoffeeSlider(sizeOptions = iconOptions,
                onSizeSelected = {
                    idx -> selectedRostIdx = idx
                    animationToken = idx
                })

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Low",
                    style = Theme.textStyle.label.small,
                    color = Theme.color.textColors.label,)
                Text(text = "Medium",
                    style = Theme.textStyle.label.small,
                    color = Theme.color.textColors.label,)
                Text(text = "High",
                    style = Theme.textStyle.label.small,
                    color = Theme.color.textColors.label,)
            }
        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)) {
            AnimatedBottomButton {
                CoffeeButton(
                    text = "Continue",
                    suffixIcon = R.drawable.ic_arrow_right,
                    onClick = onContinueClick
                )
            }
        }
    }
}



@Composable
fun AnimatedCoffeeBeans(
    token: Int,
    targetCupCenterY: Dp, // This can be negative!
    beanRes: Int,
) {
    val density = LocalDensity.current

    val startY = -300f
    val endY = with(density) { targetCupCenterY.toPx() }

    val beansY = remember { Animatable(startY) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(token) {
        beansY.snapTo(startY)
        alpha.snapTo(1f)
        beansY.animateTo(
            targetValue = endY,
            animationSpec = tween(1500)
        )
        alpha.animateTo(0f, animationSpec = tween(400))
    }

    if (alpha.value > 0.01f) {
        val minScale = 0.1f
        val maxScale = 1.4f
        // Correct fall progress calculation for any positive or negative range!
        val fallProgress = ((beansY.value - startY) / (endY - startY))
            .coerceIn(0f, 1f)
        val scale = maxScale - (maxScale - minScale) * fallProgress

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(beanRes),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer(
                        translationY = beansY.value,
                        alpha = alpha.value,
                        scaleX = scale,
                        scaleY = scale,
                    )
                    .size(90.dp)
            )
        }
    }
}

@Composable
fun AnimatedAppBar(
    modifier: Modifier = Modifier,
    enterDelayMillis: Int = 100, // e.g. 100 ms after content appears
    content: @Composable () -> Unit
) {
    val animProgress = remember { Animatable(0f) }
    val translationYMax = with(LocalDensity.current) { (-80).dp.toPx() }

    LaunchedEffect(Unit) {
        delay(enterDelayMillis.toLong())
        animProgress.animateTo(
            1f,
            animationSpec = tween(durationMillis = 500)
        )
    }
    Box(
        modifier.graphicsLayer(
            translationY = (1f - animProgress.value) * translationYMax,
            alpha = animProgress.value
        )
    ) { content() }
}

@Composable
fun AnimatedBottomButton(
    modifier: Modifier = Modifier,
    enterDelayMillis: Int = 250, // e.g. 250 ms after content appears
    content: @Composable () -> Unit
) {
    val animProgress = remember { Animatable(0f) }
    val translationYMax = with(LocalDensity.current) { 80.dp.toPx() }
    LaunchedEffect(Unit) {
        delay(enterDelayMillis.toLong())
        animProgress.animateTo(
            1f,
            animationSpec = tween(durationMillis = 500)
        )
    }
    Box(
        modifier.graphicsLayer(
            translationY = (1f - animProgress.value) * translationYMax,
            alpha = animProgress.value
        )
    ) { content() }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
fun CoffeeSizeScreenPreview() {
    CoffeeSizeScreen(
    )
}