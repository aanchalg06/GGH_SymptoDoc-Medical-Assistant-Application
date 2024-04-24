package com.aanchal.symptodoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aanchal.symptodoc.ui.theme.SymptomAppTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aanchal.symptodoc.appointmentdata.AppointmentDataViewModel
import com.aanchal.symptodoc.geminidatamanager.ChatViewModel
import com.aanchal.symptodoc.presentation.onboarding.OnBoardingScreen
import com.aanchal.symptodoc.screens.AppointmentScreen
import com.aanchal.symptodoc.screens.DoctorListScreen
import com.aanchal.symptodoc.screens.LoginScreen
import com.aanchal.symptodoc.screens.SearchScreen
import com.aanchal.symptodoc.screens.SuccessfullScreen
import com.aanchal.symptodoc.userdata.UserViewModel

class MainActivity  : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       connectivityObserver = NetworkConnectivityObserver(applicationContext)
       val doctors = DataManager.loadDoctorsFromJson(this)
       installSplashScreen()
        setContent {
            SymptomAppTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                val navController = rememberNavController()
                val viewModelmain:MainViewModel = viewModel()
                val chatViewModel =viewModel<ChatViewModel>()
                val userViewModel =viewModel<UserViewModel>()
                val chatState = chatViewModel.chatState.collectAsState().value
                val appointmentDataViewModel =viewModel<AppointmentDataViewModel>()
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {

                    NavHost(navController, startDestination="onBoarding"){
                        if(status==ConnectivityObserver.Status.Lost){
                            navController.navigate("retry")
                        }
                            composable("onBoarding"){
                                OnBoardingScreen(navController,viewModelmain)
                            }
                            composable("guestLogin"){
                                LoginScreen(userViewModel,navController)
                            }
                            composable("searchScreen"){
                                SearchScreen(navController,chatState,chatViewModel,userViewModel)
                            }
                            composable("doctorList"){
                                DoctorListScreen(navController,applicationContext,viewModelmain,doctors,chatState,chatViewModel)
                            }
                            composable("doctor_details/{doctorId}") {
                                    backStackEntry ->
                                val doctorId = backStackEntry.arguments?.getString("doctorId")?.toIntOrNull() ?: -1
                                val doctor = doctors.find { it.doctorId == doctorId } ?: Doctor(0, "", "", 0.0, "", 0, "", "", "", "", "")
                                AppointmentScreen(navController,applicationContext,viewModelmain,doctor,appointmentDataViewModel,userViewModel)
                            }
                            composable("successfullPage") {
                                SuccessfullScreen(navController,viewModelmain,appointmentDataViewModel)
                            }
                            composable("retry"){
                                chatViewModel.isLoading.value=false
                                RetryScreen(navController)
                            }

                    }
                }
            }
        }
    }
}
