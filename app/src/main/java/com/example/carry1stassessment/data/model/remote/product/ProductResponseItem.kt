package com.example.carry1stassessment.data.model.remote.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_entity")
data class ProductResponseItem(
    val currencyCode: String,
    val currencySymbol: String,
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageLocation: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: String,
    val quantityInCart: Int = 0
)