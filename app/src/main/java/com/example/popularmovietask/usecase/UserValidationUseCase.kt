package com.example.popularmovietask.usecase

import androidx.lifecycle.MutableLiveData
import com.example.popularmovietask.ktx.isValidEmail
import com.example.popularmovietask.ktx.isValidPassword
import javax.inject.Inject

/*
 * Use case class to validate user email and password
 **/
class UserValidationUseCase @Inject constructor(){
    val emailErrorLiveData = MutableLiveData<Boolean>()
     val passwordErrorLiveData = MutableLiveData<Boolean>()

    fun validateEmail(emailId: String) {
        if (emailId.isNotBlank()) {
            emailErrorLiveData.value = !emailId.isValidEmail()
        }
    }
    fun validatePassword(password: String) {
        if (password.isNotBlank()) {
            passwordErrorLiveData.value = !password.isValidPassword()
        }
    }
}