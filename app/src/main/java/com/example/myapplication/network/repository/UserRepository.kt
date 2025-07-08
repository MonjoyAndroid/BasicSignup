package com.example.myapplication.network.repository

import com.example.myapplication.model.SignupRequest
import com.example.myapplication.model.SignupResponse
import com.example.myapplication.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun signup(request: SignupRequest): Response<SignupResponse> {
        return apiService.signup(request)
    }
}
