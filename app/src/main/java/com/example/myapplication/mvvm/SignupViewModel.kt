package com.example.myapplication.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.SignupRequest
import com.example.myapplication.model.SignupResponse
import com.example.myapplication.network.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _signupResult = MutableLiveData<Result<SignupResponse>>()
    val signupResult: LiveData<Result<SignupResponse>> = _signupResult

    private var handled = false

    fun signup(request: SignupRequest) {
        viewModelScope.launch {

            handled = false

            try {
                val response = repository.signup(request)
                if (response.isSuccessful && response.body() != null) {
                    _signupResult.postValue(Result.success(response.body()!!))
                } else {
                    _signupResult.postValue(Result.failure(Throwable(response.errorBody()?.string())))
                }
            } catch (e: Exception) {
                _signupResult.postValue(Result.failure(e))
            }
        }
    }

    fun markHandled() {
        handled = true
    }

    fun isHandled(): Boolean = handled
}
