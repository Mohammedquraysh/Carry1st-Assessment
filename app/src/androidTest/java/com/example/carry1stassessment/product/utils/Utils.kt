package com.example.carry1stassessment.product.utils

import com.example.carry1stassessment.data.model.remote.product.ProductResponseItem

fun getAllFakeProducts(): List<ProductResponseItem>{
    return listOf(
        ProductResponseItem(
            id = 2,
            name = "Product 2",
            description = "Description 2",
            price = 20,
            currencyCode = "USD",
            currencySymbol = "$",
            quantity = 2,
            imageLocation = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/6b4cf068-0be3-4b9e-b741-525d46eec004.png",
            status = "ACTIVE"
        ),


        ProductResponseItem(
            id = 1,
            name = "Product 1",
            description = "Description 1",
            price = 100,
            currencyCode = "USD",
            currencySymbol = "$",
            quantity = 1,
            imageLocation = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/6b4cf068-0be3-4b9e-b741-525d46eec004.png",
            status = "ACTIVE"
        ),


        ProductResponseItem(
            id = 3,
            name = "Product 3",
            description = "Description 3",
            price = 100,
            currencyCode = "USD",
            currencySymbol = "$",
            quantity = 1,
            imageLocation = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/6b4cf068-0be3-4b9e-b741-525d46eec004.png",
            status = "ACTIVE"
        ),
    )
}



fun getProductDetails(): ProductResponseItem{
    return ProductResponseItem(
        id = 1,
        name = "Product 1",
        description = "Description 1",
        price = 100,
        currencyCode = "USD",
        currencySymbol = "$",
        quantity = 1,
        imageLocation = "https://example.com/product1.png",
        status = "ACTIVE"
    )
}