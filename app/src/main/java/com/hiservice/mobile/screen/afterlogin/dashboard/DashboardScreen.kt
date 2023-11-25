package com.hiservice.mobile.screen.afterlogin.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.R
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun DashboardContent(modifier : Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize()) {
        TopHeadBar(text = "Dashboard", onClick = {

        })
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),horizontalArrangement = Arrangement.SpaceEvenly) {
            BoxMenuComponent(image = R.drawable.service_menu, text = "Mulai Layanan")
            BoxMenuComponent(image = R.drawable.shop_menu, text = "Part Shop")
        }
        Spacer(modifier = modifier.height(16.dp))
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),horizontalArrangement = Arrangement.SpaceEvenly) {
            BoxMenuComponent(image = R.drawable.reminder_menu, text = "Riwayat Service")
            BoxMenuComponent(image = R.drawable.consult_menu, text = "E - Consult")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GetPrev() {
    HiServiceTheme {
        DashboardContent()
    }
}