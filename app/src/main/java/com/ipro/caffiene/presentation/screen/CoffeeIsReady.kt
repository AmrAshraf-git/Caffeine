package com.ipro.caffiene.presentation.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.Theme
import com.ipro.caffiene.presentation.composable.CoffeeButton
import com.ipro.caffiene.presentation.composable.CoffeeChip
import com.ipro.caffiene.presentation.composable.CoffeeToggleButton
import com.ipro.caffiene.presentation.composable.DetailsAppBar


@Composable
fun CoffeeIsReadyScreen(
    modifier: Modifier = Modifier,
    ) {
    CoffeeIsReadyContent()

}

@Composable
fun CoffeeIsReadyContent() {
    val lidDropDuration = 900
    val animProgress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        animProgress.animateTo(
            1f, animationSpec = tween(durationMillis = lidDropDuration, easing = FastOutSlowInEasing)
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),) {

        DetailsAppBar(iconRes = R.drawable.ic_cancle, isTitled = false)

        Spacer(Modifier.height(16.dp))

        AnimatedReadySection(progress = animProgress.value)


        Box(
            Modifier
                .height(341.dp)
                .fillMaxWidth()
                .clipToBounds(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Image(
                painter = painterResource(R.drawable.cup_l_with_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(260.dp)
            )
            AnimatedCupLidWithProgress(
                targetLidTopY = 4.dp,
                cupLidRes = R.drawable.cup_lid,
                lidWidth = 280.dp,
                progress = animProgress.value
            )
        }

        Spacer(Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CoffeeToggleButton()
            Text(text = "Take Away",
                style = Theme.textStyle.label.medium.copy(fontWeight = FontWeight.Bold),
                color = Theme.color.textColors.hint)
        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)) {
            CoffeeButton(
                text = "Tack Snack",
                suffixIcon = R.drawable.ic_arrow_right
            )
        }



    }
}


@Composable
fun CupCoffeeReady(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        CoffeeChip(
            buttonSize = 56.dp,
            iconRes = R.drawable.ic_check,
            contentDescription = null,
            isDropShadow = true,
            backgroundColor = Color(0xFF7C351B),
            onClick = { }
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Your coffee is ready,\n" +
                    "Enjoy",
            style = Theme.textStyle.title.large.copy(fontSize = 22.sp),
            color = Theme.color.textColors.onPrimaryBody,
            textAlign = TextAlign.Center,
        )
    }
}



//@Composable
//fun AnimatedCupLid(
//    modifier: Modifier = Modifier,
//    targetLidTopY: Dp,          // Where the lid should "land" (distance from Box top)
//    cupLidRes: Int,             // cup_lid drawable resource ID
//    delayMillis: Int = 400,     // Optional: to control animation start
//    fallDuration: Int = 850     // Optional: control fall speed
//) {
//    val density = LocalDensity.current
//    val targetLidTopYPx = with(density) { targetLidTopY.toPx() }
//    val screenHeightPx = with(density) { 600.dp.toPx() } // Large enough to ensure "off-screen"
//
//    val lidY = remember { Animatable(-screenHeightPx) }
//    val alpha = remember { Animatable(0f) }
//
//    LaunchedEffect(Unit) {
//        // Snap to initial values
//        lidY.snapTo(-screenHeightPx)
//        alpha.snapTo(0f)
//
//        // Optional: small delay before showing
//        delay(delayMillis.toLong())
//
//        // Animate alpha in (fade) a little before "fall"
//        launch {
//            alpha.animateTo(1f, animationSpec = tween(durationMillis = 200))
//        }
//
//        // Animate the lid drop
//        lidY.animateTo(
//            targetValue = targetLidTopYPx,
//            animationSpec = tween(fallDuration, easing = FastOutSlowInEasing),
//        )
//    }
//
//    if (alpha.value > 0.01f) {
//        Box(
//            modifier = modifier.fillMaxWidth(),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            Image(
//                painter = painterResource(cupLidRes),
//                contentDescription = null,
//                modifier = Modifier
//                    .graphicsLayer(
//                        translationY = lidY.value,
//                        alpha = alpha.value
//                    )
//                    .width(280.dp)
//                    .height(69.dp),
//                alignment = Alignment.TopCenter,
//                contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
//            )
//        }
//    }
//}

@Composable
fun AnimatedReadySection(progress: Float) {
    val density = LocalDensity.current
    val startY = with(density) { -600.dp.toPx() }
    val endY = 0f
    val offset = startY + (endY - startY) * progress
    val alpha = progress
    Box(
        Modifier
            .clipToBounds()
            .graphicsLayer(translationY = offset, alpha = alpha)
    ) {
        CupCoffeeReady()
    }
}

@Composable
fun AnimatedCupLidWithProgress(progress: Float, targetLidTopY: Dp, cupLidRes: Int, lidWidth: Dp) {
    val density = LocalDensity.current
    val startY = with(density) { -600.dp.toPx() }
    val endY = with(density) { targetLidTopY.toPx() }
    val lidY = startY + progress * (endY - startY)
    val alpha = progress
    Box(Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(cupLidRes),
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer(translationY = lidY, alpha = alpha)
                .width(lidWidth)
                .height(72.dp),
            alignment = Alignment.TopCenter,
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
        )
    }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
fun CoffeeIsReadyScreenPreview() {
    CoffeeIsReadyScreen(
    )
}