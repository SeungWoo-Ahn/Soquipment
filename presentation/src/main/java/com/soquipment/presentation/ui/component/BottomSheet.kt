package com.soquipment.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment
import com.soquipment.presentation.ui.theme.PrimaryColor
import com.soquipment.presentation.util.toCost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheet(
        sheetState = sheetState,
        contentWindowInsets = { WindowInsets(top = 0.dp) },
        dragHandle = {},
        onDismissRequest = onDismissRequest,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UseTimeSheet(
    scope: CoroutineScope,
    rentalTime: String,
    returnTime: String,
    onChangeRentalTime: (String) -> Unit,
    onChangeReturnTime: (String) -> Unit,
    selectUseTime: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    BottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        Spacer(20)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            CloseIcon {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    onDismissRequest()
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
        ) {
            Text(
                text = "이용시간 설정하기",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(size = 30)
            TimeItem(
                title = "대여 시간",
                time = rentalTime
            )
            TimeItem(
                title = "반납 시간",
                time = returnTime
            )
        }
        Box(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
                contentColor = Color.White
            ),
            shape = RectangleShape,
            onClick = {
                scope.launch {
                    selectUseTime()
                    sheetState.hide()
                }.invokeOnCompletion {
                    onDismissRequest()
                }
            }
        ) {
            Text(
                text = "확인",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun TimeItem(
    modifier: Modifier = Modifier,
    title: String,
    time: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.Gray
        )
        Text(
            text = time,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipmentListSheet(
    scope: CoroutineScope,
    company: Company,
    equipmentList: List<Equipment>,
    onEquipment: (Company, Equipment) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    BottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        Spacer(20)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = company.name,
                fontSize = 16.sp
            )
            CloseIcon(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    onDismissRequest()
                }
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(
                items = equipmentList,
            ) { equipment ->
                EquipmentItem(
                    equipment = equipment,
                    onEquipment = { onEquipment(company, equipment) }
                )
            }
        }
    }
}

@Composable
private fun EquipmentItem(
    modifier: Modifier = Modifier,
    equipment: Equipment,
    onEquipment: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
            .clickable(onClick = onEquipment)
    ) {
        Column(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(size = 5.dp))
        ) {
            NetworkImage(
                modifier = Modifier
                    .weight(1f),
                url = equipment.imgUrl
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .background(color = Color.LightGray),
                text = "${equipment.housePower / 1_000}kW",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
            )
        }
        Spacer(size = 20)
        Column {
            Text(
                text = equipment.brandName,
                fontSize = 14.sp,
                color = PrimaryColor,
            )
            Text(
                text = equipment.name,
                fontSize = 18.sp
            )
            Spacer(size = 16)
            Text(
                text = "${equipment.price.toCost()}원",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}