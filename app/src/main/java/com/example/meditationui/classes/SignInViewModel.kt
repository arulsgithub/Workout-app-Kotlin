package com.example.meditationui.classes

import androidx.lifecycle.ViewModel
import com.example.meditationui.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _state.update { it.copy(
            isSuccessful = result.data != null,
            signInError = result.errorMessage,
            profilePhotoUrl = result.data?.profilePictureUrl
        ) }
    }
    fun resetState(){
        _state.update { SignInState() }
    }
}