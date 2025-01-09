package com.soquipment.presentation.ui.screen.result

import androidx.lifecycle.ViewModel
import com.soquipment.domain.usecase.GetTrackingInfoUseCase
import com.soquipment.presentation.model.PaymentCart
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackingViewModel @Inject constructor(
    paymentCart: PaymentCart,
    getTrackingInfoUseCase: GetTrackingInfoUseCase,
) : ViewModel() {
    val rentalInfo = paymentCart.getRentalInfo()
    val trackingInfo = getTrackingInfoUseCase()
}