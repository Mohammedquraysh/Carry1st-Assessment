package com.example.carry1stassessment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carry1stassessment.ui.productui.components.CartListItem
import com.example.carry1stassessment.ui.productui.components.ProductDetails
import com.example.carry1stassessment.ui.productui.components.ShowProductScreen
import com.example.carry1stassessment.ui.screens.ProductsRoute
import com.example.carry1stassessment.ui.theme.Carry1stAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ProductViewModel = hiltViewModel()
            Carry1stAssessmentTheme {
                val controller = rememberNavController()
                NavHost(
                    navController = controller,
                    startDestination = ProductsRoute.ShowProductItems.route
                ) {
                    composable(ProductsRoute.ShowProductItems.route) {
                        ShowProductScreen(
                            viewModel = viewModel,
                            onNavigateToCartItems = {
                                controller.navigate(ProductsRoute.CartItems.route)
                            },
                            onItemClick = {
                                viewModel.setClickedItem(it)
                                controller.navigate(ProductsRoute.ProductDetails.route)
                            },
                        )
                    }
                    composable(ProductsRoute.ProductDetails.route) {
                        ProductDetails(
                            productViewModel = viewModel,
                            onNavigate = {
                                controller.navigate(ProductsRoute.CartItems.route)
                            },
                            onNavigateUp = {
                                controller.navigateUp()
                            }
                        )
                    }
                    composable(ProductsRoute.CartItems.route) {
                        CartListItem(
                            viewModel, onItemClick = {
                            viewModel.removeProduct(it)
                        },
                            onNavigateUp = {
                                controller.navigateUp()
                            }
                            )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Carry1stAssessmentTheme {
        Greeting("Android")
    }
}