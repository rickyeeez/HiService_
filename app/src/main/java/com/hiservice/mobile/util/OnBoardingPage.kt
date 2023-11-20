package com.hiservice.mobile.util

import androidx.annotation.DrawableRes
import com.hiservice.mobile.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.on_board_1,
        title = "Welcome to Hi-Service",
        description = "A platform that provides services that recommend workshops and spare parts stores for your vehicle needs."
    )

    object Second : OnBoardingPage(
        image = R.drawable.on_board_2,
        title = "Supported by AI technology",
        description = "The application will analyze every complaint and problem your vehicle experiences and then recommend a suitable garage."
    )

    object Third : OnBoardingPage(
        image = R.drawable.on_board_3,
        title = "Get started now and enjoy our services",
        description = "Wide reach, recommendations for the right repair shop and spare parts shop for your vehicle needs."
    )
}