package com.aanchal.symptodoc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aanchal.symptodoc.MainViewModel
import com.aanchal.symptodoc.R
import com.aanchal.symptodoc.appointmentdata.AppointmentDataViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessfullScreen(
    navController: NavHostController,
    viewModelmain: MainViewModel,
    appointmentDataViewModel: AppointmentDataViewModel
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded= false)
    var showBottomSheet by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.welcome_img),
                contentDescription = "Welcome Image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Booking Successfull",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(20.dp),
                color = Color.Black
            )
        }
        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate("searchScreen")},
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(Color(0xFF04132D)),
                content = {
                    Row {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "leftArrow",
                            tint = Color.White
                        )
                        Text(text = "Go to Homepage", color = Color.White)

                    }
                }
            )
        }
    }

}


