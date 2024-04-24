package com.aanchal.symptodoc.appointmentdata

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aanchal.symptodoc.Doctor
import java.util.Random

class AppointmentDataViewModel:ViewModel() {
    private var _doctor = mutableStateOf<List<Doctor>>(emptyList())
    val userDoctor: MutableState<List<Doctor>> = _doctor
    private val _docdate = mutableStateOf("")
    val doctdate: State<String> = _docdate
    private val _bookingNumber = mutableStateOf("")
    val bookingNumber: State<String> = _bookingNumber

    // Function to update user data
    fun updateDate(newDate: String) {
        _docdate.value = newDate
    }

    // Function to update user's doctor
    fun updateUserDoctor(newDoctorList: List<Doctor>) {
        _doctor.value = newDoctorList
    }

    // Function to generate booking number
    fun getBookingNumber() {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random()
        val bookingNum = StringBuilder()

        repeat(8) {
            bookingNum.append(characters[random.nextInt(characters.length)])
        }
        _bookingNumber.value = bookingNum.toString()
    }
}