package com.hiservice.mobile.screen.on_board

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.android.gms.analytics.AnalyticsService
import com.hiservice.mobile.R
import com.hiservice.mobile.ui.theme.HiServiceTheme
import com.hiservice.mobile.util.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    val state = rememberPagerState { 3 }
    val animationScope = rememberCoroutineScope()
    val pageList = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(), // Add weight to take the available space
            state = state
        ) { page ->
            Column {
                if(page != 0){
                    OnBoardingTop(isBack = true, isSkip = true)
                    OnBoardingField(OnBoardingPage = pageList[page])
                }else{
                    OnBoardingTop(isSkip = true)
                    OnBoardingField(OnBoardingPage = pageList[page])
                }

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Add padding for spacing
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(totalDots = 3, selectedIndex = state.currentPage, dotSize = 10.dp)
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .padding(4.dp)
                    .width(75.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFC100)),
                    ) {

            }
        }
    }
}


@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}
@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color(0xFF263238),
    unSelectedColor: Color = Color(0xFFDBDBDB) /* Color.Gray */,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}
@Composable
fun OnBoardingTop(modifier: Modifier = Modifier, isBack : Boolean = false, isSkip : Boolean = false) {
    Column {
        Spacer(modifier = modifier.width(40.dp))
        Row(modifier = modifier
            .fillMaxWidth()
            .height(120.dp).padding(all = 40.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            if(isBack){
                Icon(
                    painter = painterResource(R.drawable.back_btn),
                    contentDescription = stringResource(id = R.string.bus_content_description)
                )

            }else{
                Spacer(modifier = modifier.width(0.dp))
            }
            if(isSkip){
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = "Skip",
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }else{
                Spacer(modifier = modifier.width(0.dp))
            }
        }
    }
}
@Composable
fun OnBoardingField(modifier: Modifier = Modifier,OnBoardingPage : OnBoardingPage){
            Column(modifier = modifier.fillMaxWidth().fillMaxHeight(0.8f), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = modifier
                    .width(300.dp)
                    .height(250.dp)){
                    Image(painter = painterResource(id = OnBoardingPage.image),
                        contentDescription = stringResource(id = R.string.onBoardDesc),
                        modifier = modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit)
                }
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    text = OnBoardingPage.title,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF263238),
                    modifier = modifier.fillMaxWidth(0.5f)
                )
                Spacer(modifier = modifier.height(40.dp))
                Text(
                    text = OnBoardingPage.description,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFA6A6A6),
                    modifier = modifier.fillMaxWidth(0.8f)
                )

        }

}

@Preview
@Composable
fun GreetingPreview() {
    HiServiceTheme {
        OnBoardingScreen()

    }
}