package com.soquipment.data

import com.soquipment.domain.repository.MapRepository
import com.soquipment.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindMapRepository(mapRepositoryImpl: FakeMapRepositoryImpl) : MapRepository

    @Binds
    fun bindPaymentRepository(paymentRepositoryImpl: FakePaymentRepositoryImpl) : PaymentRepository
}