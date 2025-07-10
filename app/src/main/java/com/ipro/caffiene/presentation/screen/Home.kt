package com.ipro.caffiene.presentation.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.CaffeineTheme
import com.ipro.caffiene.designsystem.theme.Theme
import com.ipro.caffiene.presentation.composable.CoffeeButton
import com.ipro.caffiene.presentation.composable.MyAppBar

@Composable
fun HomeScreen(
    onBringMyCoffeeClick: () -> Unit = {},
) {
    HomeScreenContent(
        onBringMyCoffeeClick = onBringMyCoffeeClick
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onBringMyCoffeeClick: () -> Unit = {},
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ghostFloat")

    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatProgress"
    )

    // Offset range for ghost float
    val ghostOffsetY = lerp(0.dp, (-30).dp, animationProgress)

    // Shadow moves opposite (down when ghost is up, and vice versa)
    val shadowOffsetY = lerp(10.dp, (-10).dp, 1f - animationProgress)

    // Shadow opacity depends on ghost-shadow distance
    val shadowAlpha = lerp(1f, 0.05f, animationProgress)

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        MyAppBar()

        Spacer(modifier = Modifier.height(24.dp))

        SparklingText(animationProgress = animationProgress, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height((33).dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ghost_with_coffee),
                contentDescription = "Ghost",
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = ghostOffsetY),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Theme.color.stroke)
            )

            Image(
                painter = painterResource(id = R.drawable.ghost_shadow),
                contentDescription = "Shadow",
                modifier = Modifier
                    .size(width = 200.dp, height = 80.dp)
                    .offset(y = shadowOffsetY)
                    .graphicsLayer {
                        alpha = shadowAlpha
                    },
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Theme.color.stroke)
            )
        }

        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 50.dp)) {
            CoffeeButton(text = "bring my coffee", suffixIcon = R.drawable.ic_coffee,
                onClick =  onBringMyCoffeeClick,
            )
        }

    }


}

@Composable
fun SparklingText(modifier: Modifier = Modifier, animationProgress: Float) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Hocus", style = Theme.textStyle.body.large.copy(fontSize = 32.sp))
            Text("Pocus", style = Theme.textStyle.body.large.copy(fontSize = 32.sp))
            Text("I Need Coffee", style = Theme.textStyle.body.large.copy(fontSize = 32.sp))
            Text("to Focus", style = Theme.textStyle.body.large.copy(fontSize = 32.sp))
        }
        Sparkle(
            modifier = Modifier.align(Alignment.TopStart),
            offsetX = (254).dp,
            offsetY = (0).dp,
            alpha = animationProgress
        )
        Sparkle(
            modifier = Modifier.align(Alignment.TopStart),
            offsetX = (91).dp,
            offsetY = (193 - 140).dp,
            alpha = 1 - animationProgress
        )
        Sparkle(
            modifier = Modifier.align(Alignment.TopStart),
            offsetX = (268).dp,
            offsetY = (314 - 168).dp,
            alpha = animationProgress
        )
    }
}

@Composable
fun Sparkle(modifier: Modifier = Modifier, offsetX: Dp, offsetY: Dp, alpha: Float) {
    Icon(
        painter = painterResource(id = R.drawable.sparkle_star),
        contentDescription = "Sparkles",
        modifier = modifier
            .offset(x = offsetX, y = offsetY)
            .alpha(alpha),
        tint = Theme.color.textColors.onPrimaryBody,
    )
}


@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun HomeScreenPreview() {
    CaffeineTheme {
        HomeScreen(
        )
    }
}