package com.example.meditationui.classes

data class SignInState(
    val isSuccessful: Boolean = false,
    val signInError: String? = null,
    val profilePhotoUrl: String? = null
)