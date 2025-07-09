package com.ipro.caffiene.presentation.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.Theme
import com.ipro.caffiene.presentation.composable.DetailsAppBar

@Composable
fun CoffeeLoadingScreen(modifier: Modifier = Modifier) {

    CoffeeLoadingContent()
}

@Composable
fun CoffeeLoadingContent(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.padding(horizontal = 16.dp,),) {
            DetailsAppBar(iconRes = R.drawable.ic_arrow_left, title = "Macchiato")
        }

        Box(
            modifier = modifier
                .height(340.dp)
                .fillMaxWidth()
                .padding(16.dp,)
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 48.dp)
                    .height(244.dp)
                    .width(200.dp)
                    .align(Alignment.TopCenter),
                painter = painterResource(R.drawable.cup_l),
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
                text = "150 ml",
                style = Theme.textStyle.label.medium,
                color = Theme.color.textColors.label,
            )
        }

        SimpleLoadingBarReveal(
            loadingBarResId = R.drawable.loading_bar_p,
            backgroundColor = Theme.color.surface,
            durationMillis = 3000,
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            isLoading = true
        )


        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Almost Done",
                    fontSize = 22.sp,
                    color = Color(0xDE1F1F1F),
                    fontWeight = FontWeight.Bold,

                    )
                Text(
                    text = "Your coffee will be finish in",
                    fontSize = 16.sp,
                    color = Color(0x991F1F1F),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Row(
                    modifier = Modifier.padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.co),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Text(
                        text = ":",
                        fontSize = 40.sp,
                        color = Color(0xFF1F1F1F).copy(alpha = 0.12f),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ff),
                        contentDescription = null,
                        tint = Color.Unspecified, modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Text(
                        text = ":",
                        fontSize = 40.sp,
                        color = Color(0xFF1F1F1F).copy(alpha = 0.12f),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ee),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                }
            }
        }
    }
}

@Composable
fun SimpleLoadingBarReveal(
    loadingBarResId: Int,
    backgroundColor: Color = Theme.color.surface,
    durationMillis: Int = 1200,
    isLoading: Boolean = true,
    modifier: Modifier = Modifier
) {
    val progress = remember { Animatable(0f) }
    var forward by remember { mutableStateOf(true) }

    LaunchedEffect(isLoading) {
        while (isLoading) {
            if (forward) {
                progress.animateTo(
                    1f, animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
                )
            } else {
                progress.animateTo(
                    0f, animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
                )
            }
            forward = !forward
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(loadingBarResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(1f - progress.value)
                .background(backgroundColor)
        )
    }
}



@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360, heightDp = 800)
fun CoffeeLoadingScreenPreview() {
    CoffeeLoadingScreen()
}