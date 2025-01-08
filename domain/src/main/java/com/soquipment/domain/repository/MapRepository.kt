package com.soquipment.domain.repository

import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Point

interface MapRepository {
    fun getPointList(): List<Point>

    fun getCompany(point: Point): Company
}