package com.soquipment.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment
import com.soquipment.domain.model.Point
import com.soquipment.domain.usecase.GetCompanyListUseCase
import com.soquipment.domain.usecase.GetEquipmentListUseCase
import com.soquipment.presentation.model.PaymentCart
import com.soquipment.presentation.util.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCompanyListUseCase: GetCompanyListUseCase,
    private val getEquipmentListUseCase: GetEquipmentListUseCase,
    private val paymentCart: PaymentCart,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _rentalTime = MutableStateFlow(TimeUtil.Current.get())
    val rentalTime: StateFlow<String> = _rentalTime.asStateFlow()
    private val _pendingRentalTime = MutableStateFlow(rentalTime.value)
    val pendingRentalTime: StateFlow<String> = _pendingRentalTime.asStateFlow()

    private val _returnTime = MutableStateFlow(TimeUtil.Relative(2).get())
    val returnTime: StateFlow<String> = _returnTime.asStateFlow()
    private val _pendingReturnTime = MutableStateFlow(returnTime.value)
    val pendingReturnTime: StateFlow<String> = _pendingReturnTime.asStateFlow()

    val companyList = getCompanyListUseCase()

    fun dismiss() {
        _uiState.update { HomeUiState.Idle }
    }

    fun showUseTimeSheet() {
        _uiState.update { HomeUiState.ShowUseTimeSheet }
    }

    fun changeRentalTime(time: String) {
        _pendingRentalTime.update { time }
    }

    fun changeReturnTime(time: String) {
        _pendingReturnTime.update { time }
    }

    fun selectUseTime() {
        _returnTime.update { pendingRentalTime.value }
        _returnTime.update { pendingReturnTime.value }
    }

    fun onMarker(company: Company) {
        _uiState.update {
            HomeUiState.ShowEquipmentListSheet(
                company = company,
                equipmentList = getEquipmentListUseCase(company)
            )
        }
    }

    fun onEquipment(company: Company, equipment: Equipment, movePayment: () -> Unit) {
        paymentCart.setRentalInfo(
            company = company,
            equipment = equipment,
            rentalTime = rentalTime.value,
            returnTime = returnTime.value
        )
        movePayment()
    }
}