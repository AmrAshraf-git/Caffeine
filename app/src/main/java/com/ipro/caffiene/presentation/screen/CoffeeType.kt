package com.ipro.caffiene.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.ipro.caffiene.R
import com.ipro.caffiene.designsystem.theme.CaffeineTheme
import com.ipro.caffiene.designsystem.theme.Theme
import com.ipro.caffiene.presentation.composable.MyAppBar
import com.ipro.caffiene.presentation.composable.CoffeeButton
import com.ipro.caffiene.presentation.model.Coffee
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun CoffeeTypeScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit = {}
) {
    val demoCoffees = listOf(
        Coffee("Latte", R.drawable.coffee_latte),
        Coffee("Black", R.drawable.coffee_black),
        Coffee("Cappuccino", R.drawable.coffee_macchiato),
        Coffee("Espresso", R.drawable.coffee_espresso),
    )

    CoffeeTypeContent(
        modifier = modifier,
        coffeeList = demoCoffees,
        onContinueClick = onContinueClick)
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CoffeeTypeContent(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit = {},
    coffeeList: List<Coffee>) {
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val itemWidth = 200.dp
    //val sidePadding = (screenWidth / 2f) - (itemWidth / 2f)
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    val paddingVal = if(isLandscape) {(screenWidth * 0.35).dp} else (screenWidth*0.25).dp
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { coffeeList.size })


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(56.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            MyAppBar(modifier = Modifier.padding(bottom = 16.dp))
            Title()
        }

        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(
                horizontal = paddingVal),
            modifier = Modifier.height(300.dp),
            pageSpacing = 12.dp,
        ) {page ->
            val currentPageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue
            val scale = lerp(1.2f, 0.7f, minOf(currentPageOffset, 1f))

            CoffeeItem(
                coffee = coffeeList[page],
                selected = page == pagerState.currentPage,
                itemWidth = itemWidth,
                scale = scale,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page)
                    }
                }
            )
        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)) {
            CoffeeButton(
                text = "Continue",
                suffixIcon = R.drawable.ic_arrow_right,
                onClick = onContinueClick
            )
        }
    }
}

@Composable
fun Title() {
    Text(
        "Good Morning",
        style = Theme.textStyle.title.large.copy(fontSize = 36.sp),
        color = Theme.color.textColors.headline
    )
    Text(
        "Amr ☀",
        style = Theme.textStyle.title.large.copy(fontSize = 36.sp),
        color = Theme.color.textColors.title
    )
    Text("What would you like to drink today?",
        style = Theme.textStyle.body.medium,
        color = Theme.color.textColors.onPrimaryHint
    )
}


@Composable
fun CoffeeItem(
    coffee: Coffee,
    selected: Boolean,
    itemWidth: Dp,
    scale: Float,
    onClick: () -> Unit={}) {
    Column(
        modifier = Modifier
            .width(itemWidth)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                //transformOrigin = TransformOrigin(0.5f, 0.85f)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.clickable(indication = null
            , interactionSource = remember { MutableInteractionSource() }) { onClick() }) {
            Image(
                painter = painterResource(coffee.imageRes),
                contentDescription = coffee.name,
                modifier = Modifier
                    .width(199.dp)
                    .height(244.dp)
                    .align(Alignment.TopCenter)
            )
            Image(
                painter = painterResource(R.drawable.ic_the_chance),
                contentDescription = null,
                modifier = Modifier
                    .size(66.dp)
                    .align(Alignment.Center)
                    .offset(y = 16.dp)
            )
        }

        Text(
            coffee.name,
            style = Theme.textStyle.title.large.copy(fontSize = 32.sp),
            textAlign = TextAlign.Center
        )
    }
}



@Preview(showBackground = true, widthDp = 360, heightDp = 800, )
@Composable
fun CoffeeTypeScreenPreview() {
    val demoCoffees = listOf(
        Coffee("Latte", R.drawable.coffee_latte),
        Coffee("Black", R.drawable.coffee_black),
        Coffee("Cappuccino", R.drawable.coffee_macchiato),
        Coffee("Espresso", R.drawable.coffee_espresso),
        )

    CaffeineTheme {
        CoffeeTypeScreen(
           )
    }
}
