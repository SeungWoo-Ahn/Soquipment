package com.soquipment.domain.repository

import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.model.TrackingInfo

interface PaymentRepository {
    fun rental(rentalInfo: RentalInfo)

    fun getRandomTrackingInfo(): TrackingInfo
}