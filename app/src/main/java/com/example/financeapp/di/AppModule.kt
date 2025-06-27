package com.example.financeapp.di

import com.example.financeapp.data.remote.TransactionApi
import com.example.financeapp.data.expenses.ExpenseRemoteDataSource
import com.example.financeapp.data.expenses.ExpenseRepositoryImpl
import com.example.financeapp.domain.expenses.ExpenseRepository
import com.example.financeapp.domain.expenses.GetExpensesUseCase
import com.example.financeapp.data.repository.IncomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://shmr-finance.ru/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTransactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)

    @Provides
    @Singleton
    fun provideExpenseRemoteDataSource(api: TransactionApi): ExpenseRemoteDataSource =
        ExpenseRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideExpenseRepository(remoteDataSource: ExpenseRemoteDataSource): ExpenseRepository =
        ExpenseRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideGetExpensesUseCase(repository: ExpenseRepository): GetExpensesUseCase =
        GetExpensesUseCase(repository)

    @Provides
    @Singleton
    fun provideIncomeRepository(api: TransactionApi): IncomeRepository = IncomeRepository(api)
} 