package com.aanchal.symptodoc.screens



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.aanchal.symptodoc.userdata.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(userViewModel: UserViewModel, navController: NavHostController) {
    var name by remember { mutableStateOf(userViewModel.userName.value) }
    var phone by remember { mutableStateOf(userViewModel.userPhone.value) }
    var email by remember { mutableStateOf(userViewModel.userEmail.value) }

    // Variables to track validation status
    var isNameValid by remember { mutableStateOf(true) }
    var isPhoneValid by remember { mutableStateOf(true) }
    var isEmailValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

//        Text(text = "Create A New Account")

        androidx.compose.material.Text(
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 24.sp
                    )
                ) { append("Create a New ") }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) { append("Account") }

            },

            )
        Spacer(modifier = Modifier.height(58.dp))
        // Text fields for name, phone, and email
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                isNameValid = it.isNotBlank()
            },
            label = { Text("Enter Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xfF00659c),
                unfocusedBorderColor = Color(0xfF00659c)
            ),
            isError = !isNameValid
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                isPhoneValid = it.length == 10 && it.all { it.isDigit() }
            },
            label = { Text("Mobile NO") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xfF00659c),
                unfocusedBorderColor = Color(0xfF00659c)
            ),
            isError = !isPhoneValid
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            label = { Text("E-mail ID") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xfF00659c),
                unfocusedBorderColor = Color(0xfF00659c)
            ),
            isError = !isEmailValid
        )
        Spacer(modifier = Modifier.height(58.dp))

        // Button to proceed, enabled only if all fields are valid
        Button(
            shape = RoundedCornerShape(0),
            onClick = {
                if (isNameValid && isPhoneValid && isEmailValid) {
                    userViewModel.updateUserData(name, phone, email)
                    // Navigate to "searchScreen"
                    navController.navigate("searchScreen")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xfF00659c)
            ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Enter as a Guest", fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}
