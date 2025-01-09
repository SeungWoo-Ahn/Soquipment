package com.soquipment.presentation.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.soquipment.domain.model.Company
import com.soquipment.presentation.ui.component.ClockIcon
import com.soquipment.presentation.ui.component.EquipmentListSheet
import com.soquipment.presentation.ui.component.Spacer
import com.soquipment.presentation.ui.component.UseTimeSheet
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    movePayment: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val rentalTime by viewModel.rentalTime.collectAsStateWithLifecycle()
    val pendingRentalTime by viewModel.pendingRentalTime.collectAsStateWithLifecycle()
    val returnTime by viewModel.returnTime.collectAsStateWithLifecycle()
    val pendingReturnTime by viewModel.pendingReturnTime.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        rentalTime = rentalTime,
        returnTime = returnTime,
        companyList = viewModel.companyList,
        onMarker = viewModel::onMarker,
        showUseTimeSheet = viewModel::showUseTimeSheet
    )
    if (uiState is HomeUiState.ShowUseTimeSheet) {
        UseTimeSheet(
            scope = scope,
            rentalTime = pendingRentalTime,
            returnTime = pendingReturnTime,
            onChangeRentalTime = viewModel::changeRentalTime,
            onChangeReturnTime = viewModel::changeReturnTime,
            selectUseTime = viewModel::selectUseTime,
            onDismissRequest = viewModel::dismiss
        )
    }
    if (uiState is HomeUiState.ShowEquipmentListSheet) {
        val sheetUiState = (uiState as HomeUiState.ShowEquipmentListSheet)
        EquipmentListSheet(
            scope = scope,
            company = sheetUiState.company,
            equipmentList = sheetUiState.equipmentList,
            onEquipment = { company, equipment -> viewModel.onEquipment(company, equipment, movePayment) },
            onDismissRequest = viewModel::dismiss
        )
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun HomeScreen(
    modifier: Modifier,
    rentalTime: String,
    returnTime: String,
    companyList: List<Company>,
    onMarker: (Company) -> Unit,
    showUseTimeSheet: () -> Unit,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(37.571031, 126.978925), 15.0)
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        NaverMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            companyList.forEach { company ->
                Marker(
                    state = MarkerState(
                        position = LatLng(
                            company.point.lat.toDouble(),
                            company.point.lng.toDouble()
                        )
                    ),
                    onClick = {
                        onMarker(company)
                        true
                    }
                )
            }
        }
        UseTimeView(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(all = 20.dp),
            rentalTime = rentalTime,
            returnTime = returnTime,
            onClick = showUseTimeSheet
        )
    }
}

@Composable
private fun UseTimeView(
    modifier: Modifier,
    rentalTime: String,
    returnTime: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = 5.dp)
            )
            .padding(horizontal = 20.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClockIcon()
            Spacer(10)
            Text(
                text = "이용시간 설정하기",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            text = "${rentalTime}~${returnTime}",
            color = Color.Gray,
            fontSize = 16.sp
        )
    }
}

