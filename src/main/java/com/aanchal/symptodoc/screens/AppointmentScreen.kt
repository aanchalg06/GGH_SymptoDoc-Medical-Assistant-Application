package com.aanchal.symptodoc.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aanchal.symptodoc.Doctor
import com.aanchal.symptodoc.MainViewModel
import com.aanchal.symptodoc.R
import com.aanchal.symptodoc.appointmentdata.AppointmentDataViewModel
import com.aanchal.symptodoc.userdata.UserViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    navController: NavHostController,
    applicationContext: Context,
    viewModelmain: MainViewModel,
    doctor: Doctor,
    appointmentDataViewModel: AppointmentDataViewModel,
    userViewModel: UserViewModel
) {
    val selectedDate = appointmentDataViewModel.doctdate.value
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f),
            painter = painterResource(id = R.drawable.topsheet),
            contentDescription = "upperImage",
            alignment = Alignment.TopCenter
        )

        // Content Column
        Column(modifier = Modifier.fillMaxSize()) {
            CenterAlignedTopAppBarExample(navController,context,userViewModel,doctor)
            profileDetails(doctor)
            LazyColumn(modifier = Modifier.weight(1f)) {
                item { AboutCard(doctor) }
                item { DatePickerWithDateSelectableDatesSample(appointmentDataViewModel) }
            }
        }

        // Floating Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp) // Adjust bottom padding as needed
        ) {
            BookAppointButton(navController = navController, context = context, selectedDate = selectedDate,appointmentDataViewModel)
        }

    }
}


@Composable
fun BookAppointButton(
    navController: NavHostController,
    context: Context,
    selectedDate: String,
    appointmentDataViewModel: AppointmentDataViewModel
) {
    LargeFloatingActionButton(
        onClick = { validateAndNavigate(navController, selectedDate, context,appointmentDataViewModel) },
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(20.dp)),
        containerColor = Color(0xFF04132D),
        content = {
            Row {
                Text(text = "Book An Appointment", color = Color.White)
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "rightArrow",
                    tint = Color.White
                )
            }
        }
    )
}

fun validateAndNavigate(
    navController: NavHostController,
    selectedDate: String,
    context: Context,
    appointmentDataViewModel: AppointmentDataViewModel
) {
    // Print the selected date to debug
//    println("Selected date: ${appointmentDataViewModel.doctdate.value}")

    val dateFormatPattern = "dd-MM-yyyy"
    val dateFormat = SimpleDateFormat(dateFormatPattern, Locale.getDefault())
    dateFormat.isLenient = false

    try {
        val parsedDate = dateFormat.parse(selectedDate)
        val currentDate = Date()

        if (parsedDate != null && parsedDate.after(currentDate)) {
            appointmentDataViewModel.updateDate(selectedDate)
            navController.navigate("successfullPage")
        } else {
            Toast.makeText(context, "Please select a future date", Toast.LENGTH_SHORT).show()
        }
    } catch (e: ParseException) {
        Toast.makeText(context, "Invalid date format", Toast.LENGTH_SHORT).show()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDateSelectableDatesSample(appointmentDataViewModel: AppointmentDataViewModel) {
    val datePickerState = rememberDatePickerState()

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4FDFF),
        ),
        elevation = CardDefaults.cardElevation(60.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp, end = 20.dp, start = 20.dp)
    ) {
        DatePicker(state = datePickerState)
        if(datePickerState.selectedDateMillis!=null){
            appointmentDataViewModel.updateDate(Tools.convertLongToTime(datePickerState.selectedDateMillis!!))
        }
    }
}
class Tools {
    companion object {
        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = android.icu.text.SimpleDateFormat("dd-MM-yyyy")
            return format.format(date)
        }
    }
}

@Composable
fun AboutCard(doctor: Doctor) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4FDFF),
        )
        ,
        elevation = CardDefaults.cardElevation(60.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        val about =
            "${doctor.name} is a ${doctor.specialist}, aged ${doctor.age}, available from ${doctor.timeSlot} on weekdays in ${doctor.city}, ${doctor.location}. Feel free to Contact at ${doctor.phone} or Email at ${doctor.email} for any queries."

        Text(
            text = "About",
            fontSize = 25.sp,
            color = Color.Black,
            modifier = Modifier.padding(6.dp)
        )
        Text(text = about, fontSize = 15.sp, color = Color.Black, modifier = Modifier.padding(6.dp))
    }
}

@Composable
fun profileDetails(doctor: Doctor) {
    Row(modifier = Modifier
        .padding(25.dp)
        .fillMaxWidth()) {
        DocImageCard()
        Spacer(modifier = Modifier.padding(6.dp))
        Column() {
            Text(text = "Dr. ${doctor.name}", fontSize = 25.sp, color = Color.White, modifier = Modifier)
            Text(text = "${doctor.specialist}", fontSize = 15.sp, color = Color.White, modifier = Modifier)
            Spacer(modifier = Modifier.padding(6.dp))
            Row() {
                Box(modifier = Modifier.background(Color(0xFF00B9E4))){
                    androidx.compose.material.Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating_Star",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Text(text = "${doctor.userRating} Star",color = Color.White)
            }
        }
    }
}

@Composable
fun DocImageCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.material.MaterialTheme.colors.surface,
        ),
        modifier = Modifier
            .size(120.dp),
        shape = CircleShape
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.doc_image),
            contentDescription = "docImage",
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarExample(
    navController: NavHostController,
    context: Context,
    userViewModel: UserViewModel,
    doctor: Doctor
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(top = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        title = {
            // Add title if needed
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "backbutton",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(doctor.email))
                    putExtra(Intent.EXTRA_SUBJECT, "Subject")
                    putExtra(Intent.EXTRA_TEXT, "Message")
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                } else {
                    Toast.makeText(
                        context,
                        "No email app found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email",
                    tint = Color.White
                )
            }
        }
    )
}
