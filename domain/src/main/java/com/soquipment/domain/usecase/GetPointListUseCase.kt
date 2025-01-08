package com.soquipment.domain.usecase

import com.soquipment.domain.model.Point
import com.soquipment.domain.repository.MapRepository
import javax.inject.Inject

class GetPointListUseCase @Inject constructor(
    private val mapRepository: MapRepository,
) {
    operator fun invoke(): List<Point> = mapRepository.getPointList()
}