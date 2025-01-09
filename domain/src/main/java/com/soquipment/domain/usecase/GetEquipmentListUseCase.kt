package com.soquipment.domain.usecase

import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment
import com.soquipment.domain.repository.MapRepository
import javax.inject.Inject

class GetEquipmentListUseCase @Inject constructor(
    private val mapRepository: MapRepository,
) {
    operator fun invoke(company: Company): List<Equipment> = mapRepository.getEquipmentList(company)
}