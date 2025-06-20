package com.example.financeapp.data.remote

import com.example.financeapp.data.remote.dto.AccountBriefDto
import com.example.financeapp.data.remote.dto.TransactionResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionApi {
    @GET("accounts")
    suspend fun getAccounts(@Header("Authorization") token: String): List<AccountBriefDto>

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsForAccount(
        @Header("Authorization") token: String,
        @Path("accountId") accountId: String,
        @Query("from") from: Long,
        @Query("to") to: Long
    ): List<TransactionResponse>
} 