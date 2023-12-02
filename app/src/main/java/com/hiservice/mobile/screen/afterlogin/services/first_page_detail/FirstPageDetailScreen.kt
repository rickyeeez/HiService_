package com.hiservice.mobile.screen.afterlogin.services.first_page_detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.location.Address
import android.location.Geocoder
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.InputTextCustom
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.components.inputTextLarge
import com.hiservice.mobile.ui.theme.HiServiceTheme
import java.lang.Exception
import java.util.Locale

@Composable
fun FirstPageDetail(){
    val scrollState = rememberScrollState()
    Column (modifier = Modifier.verticalScroll(scrollState).padding(start = 26.dp, end = 26.dp, bottom = 26.dp)) {
        TopHeadBar(text = "Lokasi Anda", onClick = {

        }, isBack = true)

        Text(text = "Pilih lokasi anda : ", fontWeight = FontWeight.Medium)

        MapComponent()

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Catatan alamat : ", fontWeight = FontWeight.Medium)

        inputTextLarge(modifier = Modifier.height(190.dp))

        Spacer(modifier = Modifier.height(24.dp))

        ButtonBig(text = "Lanjut") {

        }
    }
}
@Suppress("DEPRECATION")
@Composable
fun MapComponent(modifier: Modifier = Modifier){
    val singapore = LatLng(-7.921202, 112.592091)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)

    }
    val listText = remember { mutableStateOf("Ricky") }
    val context = LocalContext.current
    Column {
        Box(modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(15.dp))){
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = {
                    try{
                        val local = Locale("id", "Indonesia")
                        val geocoder = Geocoder(context,local)
                        val getLocationName =  geocoder.getFromLocation(it.latitude,it.longitude,1)
                        listText.value = getLocationName?.get(0)?.getAddressLine(0) ?: "Lokasi tidak ditemukan"

                    }catch (e: Exception){
                        listText.value = "Tidak Ditemukan"
                    }
                }
            )
        }
        Text(text = listText.value)
    }
}


@Preview(showBackground = true)
@Composable
fun FirstPageDetailPreview() {
    HiServiceTheme {
        FirstPageDetail()
    }
}



