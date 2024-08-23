package com.example.carry1stassessment.ui.productui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem
import com.example.carry1stassessment.ui.ProductViewModel
import com.example.carry1stassessment.util.FormattedCurrencyText

@Composable
fun CartListItem(
    productViewModel: ProductViewModel,
    onItemClick: (Int) -> Unit,
    onNavigateUp: () -> Unit
    ){
    val uiState by productViewModel.uiState.collectAsStateWithLifecycle()
    NonAutoScrollableViewHeader(headerTitle = "Cart Items", body = {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
           if(uiState.productDetailList.isNotEmpty()) {
               LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)){
                   items(uiState.productDetailList){ items ->
                       CartItem(
                           itemData = items,
                           onItemClick = onItemClick,
                       )
                   }
               }
           }
        }
    }){
        onNavigateUp()
    }
}


@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    itemData: ProductResponseItem,
    onItemClick: (Int) -> Unit,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(vertical = 20.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = itemData.imageLocation, contentDescription = "${itemData.name} image",
            modifier = Modifier.size(70.dp)
            )

        Column(modifier = Modifier.padding(start = 10.dp)) {
            ReusableText(text = itemData.name)
            ReusableText(text = itemData.description)
            ReusableText(text = itemData.status)
            FormattedCurrencyText(
                input = itemData.price,
                currency =itemData.currencySymbol
            )
            ReusableText(
                text = "Cart Item Count: ${itemData.quantityInCart}",
            )

            Box{
                RedButtonOutlined(title1 = "Delete Item") {
                    onItemClick(itemData.id)
                }
            }
        }
    }
}