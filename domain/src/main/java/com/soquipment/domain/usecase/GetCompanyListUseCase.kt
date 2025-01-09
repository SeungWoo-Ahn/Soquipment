package com.soquipment.domain.usecase

import com.soquipment.domain.model.Company
import com.soquipment.domain.repository.MapRepository
import javax.inject.Inject

class GetCompanyListUseCase @Inject constructor(
    private val mapRepository: MapRepository,
) {
    operator fun invoke(): List<Company> = mapRepository.getCompanyList()
}