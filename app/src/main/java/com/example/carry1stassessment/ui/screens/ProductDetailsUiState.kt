package com.example.carry1stassessment.ui.screens

import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem

data class ProductDetailsUiState(
    val product : ProductResponseItem? = null,
    val isLoading:Boolean = false,
    val isSuccess:Boolean = false,
    val isError:Boolean = false,
    val historyDetails: List<ProductResponseItem>? = null,
    val productDetailList: List<ProductResponseItem> = emptyList()
)