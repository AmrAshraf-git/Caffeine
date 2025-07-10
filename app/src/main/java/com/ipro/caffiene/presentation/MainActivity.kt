package com.ipro.caffiene.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ipro.caffiene.designsystem.theme.CaffeineTheme
import com.ipro.caffiene.navigation.CaffeineNavGraph
import com.ipro.caffiene.presentation.screen.CoffeeIsReadyScreen
import com.ipro.caffiene.presentation.screen.CoffeeLoadingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CaffeineTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),) { innerPadding ->
                    //HomeScreen()
                    //CoffeeLoadingScreen()
                    //CoffeeIsReadyScreen()
                    CaffeineNavGraph(navController)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaffeineTheme {

    }
}