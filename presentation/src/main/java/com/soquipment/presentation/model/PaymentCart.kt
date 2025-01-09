package com.soquipment.presentation.model

import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment
import com.soquipment.domain.model.Insurance
import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.model.TransitType
import com.soquipment.domain.model.UseTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentCart @Inject constructor() {
    private val rentalInfo = MutableStateFlow<RentalInfo?>(null)

    fun setRentalInfo(company: Company, equipment: Equipment, rentalTime: String, returnTime: String) {
        rentalInfo.update {
            RentalInfo(
                equipment = equipment,
                company = company,
                useTime = UseTime(rentalTime, returnTime),
                transitType = TransitType.Direct,
                insurance = Insurance.Diamond
            )
        }
    }

    fun getRentalInfo(): RentalInfo {
        return rentalInfo.value!!
    }
}