package com.soquipment.data

import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.repository.PaymentRepository
import javax.inject.Inject

class FakePaymentRepositoryImpl @Inject constructor() : PaymentRepository {
    override fun rental(rentalInfo: RentalInfo) {
    }
}