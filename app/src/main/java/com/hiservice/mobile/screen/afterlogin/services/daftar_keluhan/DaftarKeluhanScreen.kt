package com.hiservice.mobile.screen.afterlogin.services.daftar_keluhan

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiservice.mobile.components.ButtonBig
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.fake_data.KeluhanFakeData
import com.hiservice.mobile.ui.theme.DarkCyan
import com.hiservice.mobile.ui.theme.HiServiceTheme

@Composable
fun DaftarKeluhan(modifier: Modifier = Modifier){
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)){
        TopHeadBar(text = "Daftar Keluhan", onClick = {

        }, isBack = true)

        Column (modifier = modifier.padding(start = 26.dp, end = 26.dp, bottom = 26.dp)){
            Text(text = "Pilih minimal 3 gejala pada motor", fontWeight = FontWeight.Medium)

            LazyColumn (modifier = modifier
                .height(600.dp)
                .border(1.dp, DarkCyan)
                .clip(RoundedCornerShape(15.dp))
            ){
                items(KeluhanFakeData.listKeluhan, key = {it.id}){keluhan ->
                    DaftarKeluhanContent(namaKeluhan = keluhan.namaKeluhan,modifier = Modifier.fillMaxWidth())
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ButtonBig(text = "Lanjut") {
                
            }
        }
    }
}

@Composable
fun DaftarKeluhanContent(
    namaKeluhan: String,
    modifier: Modifier = Modifier
){
    val checked = remember { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {
            checked.value = !checked.value // Ubah nilai checked saat row diklik
        }
    ){
        Checkbox(
            checked = checked.value,
            onCheckedChange = { isChecked -> checked.value = isChecked },
            modifier = Modifier.clickable { checked.value = !checked.value } // Jangan reaktif saat checkbox diklik
        )
        Text(text = namaKeluhan, modifier = Modifier.padding(start = 8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun DaftarKeluhanPreview(){
    HiServiceTheme {
        DaftarKeluhan()
    }
}

@Preview(showBackground = true)
@Composable
fun DaftarKeluhanContentPreview(){
    HiServiceTheme {
        DaftarKeluhanContent(namaKeluhan = "Knalpot berasap tebal")
    }
}