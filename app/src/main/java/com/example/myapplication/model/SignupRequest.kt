package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("firstName") var fName: String,
    @SerializedName("lastName")var lName: String,
    @SerializedName("age")var age: Int
)

data class SignupResponse(
//    val success: Boolean,
//    val message: String?,
    @SerializedName("id")var userId: Int,
    @SerializedName("firstName") var fName: String,
    @SerializedName("lastName")var lName: String,
    @SerializedName("age")var age: Int
)
