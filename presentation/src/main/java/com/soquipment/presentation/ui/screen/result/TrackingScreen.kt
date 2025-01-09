package com.soquipment.presentation.ui.screen.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.model.TrackingInfo
import com.soquipment.presentation.ui.component.CircleChart
import com.soquipment.presentation.ui.component.SoTopBar
import com.soquipment.presentation.ui.component.Spacer
import com.soquipment.presentation.ui.screen.payment.EquipmentInfo
import com.soquipment.presentation.ui.screen.payment.Label

@Composable
fun TrackingRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: TrackingViewModel = hiltViewModel(),
) {
    TrackingScreen(
        modifier = modifier,
        rentalInfo = viewModel.rentalInfo,
        trackingInfo = viewModel.trackingInfo,
        onBack = onBack
    )
}

@Composable
private fun TrackingScreen(
    modifier: Modifier,
    rentalInfo: RentalInfo,
    trackingInfo: TrackingInfo,
    onBack: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SoTopBar(
            title = "실시간 트래킹",
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(all = 20.dp)
        ) {
            EquipmentInfo(
                equipment = rentalInfo.equipment,
                company = rentalInfo.company,
                useTime = rentalInfo.useTime
            )
            HorizontalDivider(thickness = (0.5).dp, color = Color.LightGray)
            TrackingInfoView(trackingInfo = trackingInfo)
        }
    }
}

@Composable
private fun TrackingInfoView(
    modifier: Modifier = Modifier,
    trackingInfo: TrackingInfo,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(size = 40)
        Label(text = "트래킹 정보")
        Spacer(size = 30)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                InfoItem(
                    title = "RPM",
                    amount = trackingInfo.rpm,
                    unit = "rpm"
                )
            }
            item {
                InfoItem(
                    title = "연료량",
                    amount = trackingInfo.fuel,
                    unit = "L"
                )
            }
            item {
                ChartItem(
                    title = "연료 효율",
                    progress = trackingInfo.fuelEfficiency
                )
            }
            item {
                ChartItem(
                    title = "작업 효율",
                    progress = trackingInfo.workEfficiency
                )
            }
        }
    }
}

@Composable
private fun InfoItem(
    modifier: Modifier = Modifier,
    title: String,
    amount: Int,
    unit: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = Color.Gray
        )
        Spacer(size = 20)
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "$amount",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(size = 2)
            Text(
                text = unit,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun ChartItem(
    modifier: Modifier = Modifier,
    title: String,
    progress: Float
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = Color.Gray
        )
        Spacer(size = 20)
        CircleChart(progress = progress)
        Spacer(size = 20)
        Text(
            text = "${(progress * 100).toInt()}%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}