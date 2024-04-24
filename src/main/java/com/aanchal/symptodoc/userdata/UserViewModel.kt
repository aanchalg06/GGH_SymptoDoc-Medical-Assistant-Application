package com.aanchal.symptodoc.userdata

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName
    private val _userPhone = mutableStateOf("")
    val userPhone: State<String> = _userPhone
    private val _userEmail = mutableStateOf("")
    val userEmail: State<String> = _userEmail

    // Function to update user data
    fun updateUserData(newName: String, newPhone: String, newEmail: String) {
        _userName.value = newName
        _userPhone.value = newPhone
        _userEmail.value = newEmail
    }

}
