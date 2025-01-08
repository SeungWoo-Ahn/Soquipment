package com.soquipment.domain.model

enum class TransitType(val price: Int) {
    Direct(price = 0),
    Delivery(price = 100_000),
}