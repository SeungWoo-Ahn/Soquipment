package com.soquipment.domain.repository

import com.soquipment.domain.model.Company
import com.soquipment.domain.model.Equipment

interface MapRepository {
    fun getCompanyList(): List<Company>

    fun getEquipmentList(company: Company): List<Equipment>
}