package com.example.carry1stassessment.ui.productui.components

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carry1stassessment.R

@Composable
fun ViewHeader(
    headerTitle: String,
    body: @Composable () -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "backward arrow",
                modifier = Modifier
                    .clickable {
                        onBackPressed()
                    }
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = headerTitle,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF021218),
                )
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            body()
        }
    }
}






@Composable
fun ReusableRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
) {
    Row(
        modifier = modifier
            .padding(top = 5.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        ReusableText(text = label)
        ReusableText(text = value)
    }
}


@Composable
fun RedButtonOutlined(title1: String, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .height(60.dp)
            .border(
                border = BorderStroke(2.dp, Color(0xFFF21B1B)),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ReusableText(text = title1, color = Color(0xFFF21B1B))
    }
}


@Composable
fun RedButtonFilled(title1: String, isEnabled: Boolean = true, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = if (isEnabled) Color(0xFFF21B1B) else Color(0xFFF59797),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clickable {
                if (isEnabled)
                    onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title1,
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF)
            )
        )
    }
}


@Composable
fun ReusableText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 18.sp,
    lineHeight: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight(500),
    color: Color = Color(0xFF021218)
) {
    Text(
        modifier = modifier.padding(top = 10.dp),
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            lineHeight = lineHeight,
            fontWeight = fontWeight,
            color = color
        )
    )
}


@Composable
fun NonAutoScrollableViewHeader(
    headerTitle: String,
    hasImage: Boolean = false,
    count: Int = 0,
    body: @Composable () -> Unit,
    onNavigateToCartItems: () -> Unit = {},
    onBackPressed: () -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!hasImage) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "backward arrow",
                    modifier = Modifier
                        .clickable {
                            onBackPressed()
                        }
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(
                modifier = Modifier.weight(1f),
                text = headerTitle,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF021218),
                )
            )
            if (hasImage) {
                Box(
                    modifier = Modifier
                        .size(30.dp).clickable {
                            println("CALLED")
                            onNavigateToCartItems.invoke()
                        }) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(start = 2.dp),
                        contentAlignment = Alignment.TopEnd) {
                       Text(
                           text = "$count", color = Color.Red)
                    }
                    Box{
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Cart Items",
                        modifier = Modifier.size(25.dp)
                    )
                }}
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            body()
        }
    }
}


@Composable
fun LoadingScreen(
    message: String = "Loading, please wait..."
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AppLoader()
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = message, color = Color.Black)
        }
    }
}

@Composable
fun AppLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "label")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.12f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = 1_000, easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"
    )
    Box(
        contentAlignment = Alignment.Center,
    ) {
//        CircularProgressIndicator(
//            modifier = Modifier.size(80.dp),
//            trackColor = AppColor,
//            color = Color.White,
//            strokeWidth = 7.dp
//        )
        Image(
            modifier = Modifier
                .size(30.dp)
                .scale(scale),
            painter = painterResource(id = R.drawable.carry_first),
            contentDescription = null
        )
    }
}


