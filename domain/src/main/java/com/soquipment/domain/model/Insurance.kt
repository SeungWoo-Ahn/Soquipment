package com.soquipment.domain.model

enum class Insurance(val upperPrice: Int, val price: Int) {
    Diamond(upperPrice = 0, price = 20_890),
    Platinum(upperPrice = 5, price = 15_330),
    Gold(upperPrice = 30, price = 10_130),
    Silver(upperPrice = 70, price = 8_030)
}