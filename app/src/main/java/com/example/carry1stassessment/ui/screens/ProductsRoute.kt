package com.example.carry1stassessment.ui.screens


sealed class ProductsRoute(val route: String) {
    data object ShowProductItems : ProductsRoute("product_items")
    data object ProductDetails : ProductsRoute("product_details")
    data object CartItems : ProductsRoute("cart_items")
}