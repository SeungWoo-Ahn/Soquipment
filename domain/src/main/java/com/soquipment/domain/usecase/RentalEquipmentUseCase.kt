package com.soquipment.domain.usecase

import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.repository.PaymentRepository
import javax.inject.Inject

class RentalEquipmentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
) {
    operator fun invoke(rentalInfo: RentalInfo) = paymentRepository.rental(rentalInfo)
}