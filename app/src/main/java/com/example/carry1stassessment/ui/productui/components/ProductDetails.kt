package com.example.carry1stassessment.ui.productui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.carry1stassessment.R
import com.example.carry1stassessment.ui.ProductViewModel

@Composable
fun ProductDetails(
    productViewModel: ProductViewModel,
    onNavigate: () -> Unit,
    onNavigateUp: () -> Unit,
){
    val uiState = productViewModel.uiState.collectAsStateWithLifecycle().value
    ViewHeader(headerTitle = "Product Details", body = {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Column(Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = uiState.product?.imageLocation.toString(),
                    contentDescription = "${uiState.product?.name} image",
                    modifier = Modifier.size(50.dp).padding(top = 20.dp)
                )

            }
            ReusableRow(label = stringResource(id = R.string.product_name), value = uiState.product?.name.toString())
            ReusableRow(label = stringResource(id = R.string.product_description), value = uiState.product?.description.toString())
            ReusableRow(label =  stringResource(id = R.string.product_price), value = uiState.product?.price.toString())
            Row(
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ReusableText(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    text = "Quantity in cart:",
                )
                Spacer(modifier = Modifier.weight(1f))

                if (uiState.product?.quantityInCart!! >= 1){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(26.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ReusableText(
                            text = "+",
                            modifier = Modifier.clickable {
                                if ((uiState.product.quantity) <= (uiState.product.quantityInCart)){
                                    Toast.makeText(context, "You cannot add more items than are currently in stock.", Toast.LENGTH_SHORT).show()
                                    }else{
                                    productViewModel.insertProduct((uiState.product.quantityInCart) + 1)
                                }
                            }
                        )
                        ReusableText(
                            text = uiState.product.quantityInCart.toString(),
                        )
                        ReusableText(
                            text = "-",
                            modifier = Modifier.clickable {
                                if (uiState.product.quantityInCart > 1){
                                    productViewModel.insertProduct((uiState.product.quantityInCart) - 1)
                                }else{
                                    Toast.makeText(context, "Please go ahead and remove the item from the cart", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }else{
                    ReusableText(
                        modifier = Modifier.padding(top = 10.dp),
                        text = uiState.product.quantityInCart.toString(),
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 50.dp))
            RedButtonFilled(
                title1 = if (uiState.product?.quantityInCart == 0) "Add to Cart" else "View items in Cart",
                isEnabled = true
            ){
                if (uiState.product?.quantityInCart == 0){
                    productViewModel.insertProduct((uiState.product.quantityInCart).plus(1))
                    Toast.makeText(context, "Item Added to Cart", Toast.LENGTH_SHORT).show()
                    onNavigateUp()
                    return@RedButtonFilled
                }else{
                    onNavigate()
                }
            }
            RedButtonOutlined(title1 = "Buy Now") {}
        }
    }){
        onNavigateUp()
    }
}