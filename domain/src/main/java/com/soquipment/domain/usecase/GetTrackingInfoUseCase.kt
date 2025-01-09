package com.soquipment.domain.usecase

import com.soquipment.domain.model.TrackingInfo
import com.soquipment.domain.repository.PaymentRepository
import javax.inject.Inject

class GetTrackingInfoUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
) {
    operator fun invoke(): TrackingInfo = paymentRepository.getRandomTrackingInfo()
}