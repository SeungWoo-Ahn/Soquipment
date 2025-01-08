package com.soquipment.domain.usecase

import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Point
import com.soquipment.domain.repository.MapRepository
import javax.inject.Inject

class GetCompanyUseCase @Inject constructor(
    private val mapRepository: MapRepository,
) {
    operator fun invoke(point: Point): Company = mapRepository.getCompany(point)
}