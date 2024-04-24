package com.aanchal.symptodoc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aanchal.symptodoc.R
import com.aanchal.symptodoc.geminidatamanager.ChatState
import com.aanchal.symptodoc.geminidatamanager.ChatUIEvent
import com.aanchal.symptodoc.geminidatamanager.ChatViewModel
import com.aanchal.symptodoc.userdata.UserViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    chatState: ChatState,
    chatViewModel: ChatViewModel,
    userViewModel: UserViewModel
) {

    Box(modifier = Modifier.fillMaxSize()) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f),
                painter = painterResource(id = R.drawable.topsheet),
                contentDescription = "upperImage",
                alignment = Alignment.TopCenter
            )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
//          //
            Column(modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.padding(30.dp))// Allow this column to take up remaining space
                Text(text = "Hello,",modifier = Modifier,fontSize = 20.sp,color = Color.White)
                Text(text = "${userViewModel.userName.value}",fontSize = 25.sp,modifier = Modifier.padding(vertical = 6.dp),style = LocalTextStyle.current, fontWeight = FontWeight.ExtraBold, color = Color.White)
            }
            Column(modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start) {
                SearchBarCard(chatViewModel,chatState){

                        navController.navigate("doctorList")
                }
            }
            Spacer(modifier = Modifier.padding(50.dp))
            println(chatState.chatList.toString())

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCard(chatViewModel: ChatViewModel, chatState: ChatState, onClick: () -> Unit) {


    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF013E5e),
        ),
        elevation= CardDefaults.cardElevation(100.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Elaborate Symptoms To Get Specialist of Disease",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp),
            color = Color.White
        )
        Box {
            // Search bar field with trailing icon button
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                value = chatState.prompt,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.Transparent,
                    containerColor = Color(0xFFFFFFFF),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                onValueChange = { chatViewModel.onEvent(ChatUIEvent.UpdatePrompt(it)) },
                shape = RoundedCornerShape(50.dp),
                placeholder = {
                    Text(text = "Elaborate Symptoms here!", fontSize = 10.sp)
                },
                singleLine = false,
                trailingIcon = {

                    if (chatViewModel.isLoading.value) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(24.dp),
                            color = Color.White
                        )
                    } else {
                        IconButton(onClick = {
                            chatViewModel.isLoading.value = true
                            chatViewModel.onEvent(ChatUIEvent.SendPrompt('"'+chatState.prompt+'"'+", for these symptoms give me specialist from this list Cardiologist, Dermatologist, Endocrinologist, Gastroenterologist, Hematologist, Obstetrician, Gynecologist, Oncologist, Ophthalmologist, Orthopedic Surgeon, Otolaryngologist, Pediatrician, Psychiatrist, Pulmonologist, Rheumatologist, Urologist, Allergist, Immunologist, Infectious Disease Specialist, Nephrologist, Neurologist, Physical Therapist whom I should contact. Give me comma seperated specilaist only, nothing else"))
                            onClick()
                        }) {
                            // Icon you want to display

                            Icon(
                                imageVector = Icons.Filled.Send,
                                contentDescription = "send_button",
                                tint = Color(0xFF08007C)
                            )
                        }
                    }
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun greet() {
//    val navController = rememberNavController()
//
//    val chatViewModel =viewModel<ChatViewModel>()
//    val userViewModel =viewModel<UserViewModel>()
//    val chatState = chatViewModel.chatState.collectAsState().value
//
//    SearchScreen(navController,chatState,chatViewModel,userViewModel)
//}
