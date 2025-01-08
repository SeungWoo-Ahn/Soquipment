package com.soquipment.domain.repository

import com.soquipment.domain.model.RentalInfo

interface PaymentRepository {
    fun rental(rentalInfo: RentalInfo)
}