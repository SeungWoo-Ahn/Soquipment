package com.soquipment.presentation.ui.screen.payment

import androidx.lifecycle.ViewModel
import com.soquipment.domain.model.Insurance
import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.model.TransitType
import com.soquipment.domain.usecase.RentalEquipmentUseCase
import com.soquipment.presentation.model.PaymentCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val rentalEquipmentUseCase: RentalEquipmentUseCase,
    paymentCart: PaymentCart,
) : ViewModel() {
    private val _rentalInfo = MutableStateFlow(paymentCart.getRentalInfo())
    val rentalInfo: StateFlow<RentalInfo> = _rentalInfo.asStateFlow()

    fun onTransitType(transitType: TransitType) {
        if (rentalInfo.value.transitType != transitType) {
            _rentalInfo.update {
                rentalInfo.value.copy(transitType = transitType)
            }
        }
    }

    fun onInsurance(insurance: Insurance) {
        if (rentalInfo.value.insurance != insurance) {
            _rentalInfo.update {
                rentalInfo.value.copy(insurance = insurance)
            }
        }
    }

    fun pay(moveTacking: () -> Unit) {
        rentalEquipmentUseCase(rentalInfo.value)
        moveTacking()
    }
}