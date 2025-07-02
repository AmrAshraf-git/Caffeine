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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.CaffeineTheme
import com.ipro.caffiene.designsystem.theme.Theme

@Composable
fun HomeScreen() {
    AnimatedGhostWithSparkles()
}

@Composable
fun AnimatedGhostWithSparkles() {
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        // Sparkles + Text
        SparklingText(animationProgress = animationProgress)

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.ghost_with_coffee),
            contentDescription = "Ghost",
            modifier = Modifier
                .size(200.dp)
                .offset(y = ghostOffsetY)
        )

        Image(
            painter = painterResource(id = R.drawable.ghost_shadow),
            contentDescription = "Shadow",
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .offset(y = shadowOffsetY)
                .graphicsLayer {
                    alpha = shadowAlpha
                }
        )
    }


}
@Composable
fun SparklingText(animationProgress: Float) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Hocus", style = Theme.textStyle.title.medium)
            Text("Pocus", style = MaterialTheme.typography.headlineMedium)
            Text("I Need Coffee", style = MaterialTheme.typography.headlineMedium)
            Text("to Focus", style = MaterialTheme.typography.headlineMedium)
        }

        // ✻ Sparkles fading in and out with ghost
        Sparkle(offsetX = -100.dp, offsetY = -80.dp, alpha = animationProgress)
        Sparkle(offsetX = 90.dp, offsetY = -60.dp, alpha = 1 - animationProgress)
        Sparkle(offsetX = -60.dp, offsetY = 60.dp, alpha = animationProgress)
    }
}

@Composable
fun Sparkle(offsetX: Dp, offsetY: Dp, alpha: Float) {
    Text(
        text = "✻",
        fontSize = 14.sp,
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .alpha(alpha),
        color = Color.Gray
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CaffeineTheme {
        HomeScreen()
    }
}