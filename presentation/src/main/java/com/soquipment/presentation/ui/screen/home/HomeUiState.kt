package com.soquipment.presentation.ui.screen.home

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment

@Stable
sealed interface HomeUiState {
    @Immutable
    data object Idle : HomeUiState

    @Immutable
    data object ShowUseTimeSheet : HomeUiState

    @Immutable
    data class ShowEquipmentListSheet(
        val company: Company,
        val equipmentList: List<Equipment>
    ) : HomeUiState
}