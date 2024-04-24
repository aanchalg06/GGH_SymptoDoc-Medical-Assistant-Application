package com.aanchal.symptodoc

import kotlinx.serialization.Serializable

@Serializable
data class Doctor(
    val doctorId: Int,
    val specialist: String,
    val timeSlot: String,
    val userRating: Double,
    val name: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val location: String,
    val city: String
)

