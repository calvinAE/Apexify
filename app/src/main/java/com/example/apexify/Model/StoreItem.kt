package com.example.apexify.Model

data class StoreItem(
    val asset: String,
    val content: List<Content>,
    val desc: String,
    val expireTimestamp: Int,
    val isAvailable: Boolean,
    val offerID: String,
    val originalPrice: Int,
    val pricing: List<Pricing>,
    val purchaseLimit: Int,
    val shopType: String,
    val tag: String,
    val title: String
)