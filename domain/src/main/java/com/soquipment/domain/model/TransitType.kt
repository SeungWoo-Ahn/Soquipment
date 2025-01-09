package com.soquipment.domain.model

enum class TransitType(val title: String, val price: Int) {
    Direct(title = "직접 수령", price = 0),
    Delivery(title = "배송", price = 100_000),
}