package com.example.carry1stassessment.ui.productui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.carry1stassessment.R
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.ui.ProductViewModel
import com.example.carry1stassessment.util.FormattedCurrencyText


object ProductScreenTestTag{
    const val GETLIST = "get_list"

}
@Composable
fun ShowProductScreen(
    viewModel: ProductViewModel,
    onNavigateToCartItems: () -> Unit,
    onItemClick: (ProductResponseItem) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getAllProductDetails()
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NonAutoScrollableViewHeader(
        headerTitle = "Product Items",hasImage = true, count = uiState.productDetailList.size,
        onNavigateToCartItems = onNavigateToCartItems,body = {
        val itemsData = uiState.historyDetails ?: emptyList()
        if (uiState.isLoading) {
            LoadingScreen()
            return@NonAutoScrollableViewHeader
        }

        if (itemsData.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.testTag(ProductScreenTestTag.GETLIST)
            ) {
                items(itemsData){ item ->
                    ProductItem(
                        historyData = item,
                        onItemClick = onItemClick,
                    )
                }
            }
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.image_description),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .weight(1f),
                    text = stringResource(id = R.string.image_description),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF021218),
                    )
                )
            }
        }

    }) {

    }
}


@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    historyData: ProductResponseItem,
    onItemClick: (ProductResponseItem) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .clickable { onItemClick.invoke(historyData) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = historyData.imageLocation,
            contentDescription = "${historyData.name} image",
            modifier = Modifier.size(50.dp)
            )

        Column(modifier = Modifier.padding(start = 10.dp)) {
            ReusableText(text = historyData.name)
            FormattedCurrencyText(
                input = historyData.price,
                currency = historyData.currencySymbol
            )
        }
    }
}




