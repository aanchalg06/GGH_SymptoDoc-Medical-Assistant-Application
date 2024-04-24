package com.aanchal.symptodoc.presentation.onboarding

import androidx.annotation.DrawableRes
import com.aanchal.symptodoc.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "AI-powered care ",
        description = "AI is here to analyze your symptoms and provide you with insights.",
        image = R.drawable.first
    ),
    Page(
    title = "Get Diagnoise ",
        description = "Write your mock symptoms",
        image = R.drawable.second
    ),
    Page(
    title = "Make Appointment with Specialist ",
    description = "Know disease and meet specialist",
    image = R.drawable.third
    )
)