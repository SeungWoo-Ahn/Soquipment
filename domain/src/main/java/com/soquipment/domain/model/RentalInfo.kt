package com.soquipment.domain.model

data class RentalInfo(
    val equipment: Equipment,
    val company: Company,
    val useTime: UseTime,
    val transitType: TransitType,
    val insurance: Insurance,
) {
    val totalPrice = equipment.price + transitType.price + insurance.price
}
