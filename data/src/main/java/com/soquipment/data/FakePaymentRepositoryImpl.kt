package com.soquipment.data

import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.model.TrackingInfo
import com.soquipment.domain.repository.PaymentRepository
import javax.inject.Inject

class FakePaymentRepositoryImpl @Inject constructor() : PaymentRepository {
    override fun rental(rentalInfo: RentalInfo) {
    }

    override fun getRandomTrackingInfo(): TrackingInfo {
        return TrackingInfo(
            rpm = (600..4000).random(),
            fuel = (0..600).random(),
            fuelEfficiency = (30..100).random() / 100f,
            workEfficiency = (30..100).random() / 100f
        )
    }
}