package com.soquipment.presentation.ui.screen.payment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment
import com.soquipment.domain.model.Insurance
import com.soquipment.domain.model.RentalInfo
import com.soquipment.domain.model.TransitType
import com.soquipment.domain.model.UseTime
import com.soquipment.presentation.ui.component.NetworkImage
import com.soquipment.presentation.ui.component.SoTopBar
import com.soquipment.presentation.ui.component.Spacer
import com.soquipment.presentation.ui.theme.PrimaryColor

@Composable
fun PaymentRoute(
    modifier: Modifier = Modifier,
    moveTracking: () -> Unit,
    onBack: () -> Unit,
    viewModel: PaymentViewModel = hiltViewModel(),
) {
    val rentalInfo by viewModel.rentalInfo.collectAsStateWithLifecycle()

    PaymentScreen(
        modifier = modifier,
        rentalInfo = rentalInfo,
        onTransitType = viewModel::onTransitType,
        onInsurance = viewModel::onInsurance,
        onPay = { viewModel.pay(moveTracking) },
        onBack = onBack
    )
}

@Composable
private fun PaymentScreen(
    modifier: Modifier,
    rentalInfo: RentalInfo,
    onTransitType: (TransitType) -> Unit,
    onInsurance: (Insurance) -> Unit,
    onPay: () -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SoTopBar(
            title = "예약 및 결제하기",
            onBack = onBack
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(state = rememberScrollState())
                .padding(all = 20.dp)
        ) {
            EquipmentInfo(
                equipment = rentalInfo.equipment,
                company = rentalInfo.company,
                useTime = rentalInfo.useTime
            )
            HorizontalDivider(thickness = (0.5).dp, color = Color.LightGray)
            TransitSelectView(
                transitType = rentalInfo.transitType,
                onTransitType = onTransitType
            )
            HorizontalDivider(thickness = (0.5).dp, color = Color.LightGray)
            InsuranceSelectView(
                insurance = rentalInfo.insurance,
                onInsurance = onInsurance
            )
            PayButtonView(
                cost = rentalInfo.totalPrice,
                onPay = onPay
            )
        }
    }
}

@Composable
private fun EquipmentInfo(
    modifier: Modifier = Modifier,
    equipment: Equipment,
    company: Company,
    useTime: UseTime,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Label(text = "장비 정보")
        Spacer(size = 10)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                modifier = Modifier.size(80.dp),
                url = equipment.imgUrl
            )
            Spacer(size = 10)
            Column {
                Text(
                    text = equipment.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${equipment.price}원",
                    fontSize = 14.sp,
                    color = PrimaryColor
                )
            }
        }
        Spacer(size = 20)
        Info(
            title = "대여·반납 장소",
            info = company.name
        )
        Info(
            title = "이용 시간",
            info = "${useTime.rentalTime}~${useTime.returnTime}"
        )
        Spacer(size = 40)
    }
}

@Composable
private fun TransitSelectView(
    modifier: Modifier = Modifier,
    transitType: TransitType,
    onTransitType: (TransitType) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(size = 40)
        Label(text = "수령 방식 선택")
        Spacer(size = 10)
        TransitType.entries.forEach { item ->
            SelectView(
                title = item.title,
                additionalCost = item.price,
                isSelect = transitType == item,
                onSelect = { onTransitType(item) }
            )
        }
        Spacer(size = 40)
    }
}

@Composable
private fun InsuranceSelectView(
    modifier: Modifier = Modifier,
    insurance: Insurance,
    onInsurance: (Insurance) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(size = 40)
        Label(text = "장비손해면책상품")
        Spacer(size = 10)
        Insurance.entries.forEach { item ->
            val suffix = if (item.upperPrice == 0) "없음" else "최대 ${item.upperPrice}만원"
            SelectView(
                title = "자기부담금 $suffix",
                additionalCost = item.price,
                isSelect = insurance == item,
                onSelect = { onInsurance(item) }
            )
        }
        Spacer(size = 40)
    }
}

@Composable
private fun PayButtonView(
    modifier: Modifier = Modifier,
    cost: Int,
    onPay: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Button(
          modifier = Modifier
              .fillMaxWidth()
              .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
                contentColor = Color.White
            ),
            shape = RectangleShape,
            onClick = onPay
        ) {
            Text(
                text = "${cost}원 결제하기",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Label(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Info(
    title: String,
    info: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color.Gray,
            fontSize = 16.sp
        )
        Text(
            text = info,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SelectView(
    title: String,
    additionalCost: Int,
    isSelect: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable(onClick = onSelect),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelect,
                onClick = null,
                colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor)
            )
            Spacer(size = 10)
            Text(text = title)
        }
        Text(
            text = "+${additionalCost}원",
            color = if (isSelect) Color.Black else Color.Gray
        )
    }
}