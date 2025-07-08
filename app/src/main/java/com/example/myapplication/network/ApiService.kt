package com.example.myapplication.network

import com.example.myapplication.model.SignupRequest
import com.example.myapplication.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users/add")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<SignupResponse>
}