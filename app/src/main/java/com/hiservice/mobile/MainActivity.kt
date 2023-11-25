package com.hiservice.mobile

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hiservice.mobile.screen.on_board.OnBoardingScreen
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.services.finalsubmissionjetpackcompose.ui.navigation.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        setContent {
            HiServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HiService()
                }
            }
        }
    }
}
@Composable
fun HiService(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
        NavHost(
            navController = navController,
            startDestination = Screen.OnBoard.route
        ) {
            composable(Screen.OnBoard.route) {
                OnBoardingScreen()
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HiServiceTheme {

    }
}