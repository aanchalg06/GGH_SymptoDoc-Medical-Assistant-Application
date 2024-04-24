package com.aanchal.symptodoc.screens


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aanchal.symptodoc.Doctor
import com.aanchal.symptodoc.MainViewModel
import com.aanchal.symptodoc.R
import com.aanchal.symptodoc.geminidatamanager.ChatState
import com.aanchal.symptodoc.geminidatamanager.ChatViewModel

@Composable
fun DoctorListScreen(
    navController: NavHostController,
    applicationContext: Context,
    viewModelmain: MainViewModel,
    doctors: List<Doctor>,
    chatState: ChatState,
    chatViewModel: ChatViewModel
) {
    var context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        if (chatViewModel.isLoading.value == false) {
            // Get the categories from the response message
            val categories = chatViewModel.responseMessage.value.split(", ")

            // Convert medicalSpecialties to a Set for efficient lookup
            val medicalSpecialtiesSet = viewModelmain.medicalSpecialties.toSet()

            // Check if all categories are present in medicalSpecialties
            val missingCategories = categories.filter { category ->
                category !in medicalSpecialtiesSet
            }

            if (missingCategories.isEmpty() && categories!=null) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.8f),
                    painter = painterResource(id = R.drawable.topsheet),
                    contentDescription = "upperImage",
                    alignment = Alignment.TopCenter
                )
                val selectedCategory = remember { mutableStateOf(categories.getOrElse(0) { "" }) }
                Column(modifier = Modifier.fillMaxSize()) {
                    DoctorNav(navController)
                    // Display CategoryItemsScreen if no category is selected
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                    ) {
                        Column(modifier = Modifier) {
                            Text(
                                text = "Specialist",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = Color.White,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                            Spacer(modifier = Modifier.padding(6.dp))
                            CategoryItemsScreen(
                                categories = categories,
                                selectedCategory = selectedCategory
                            )
                        }
                    }
                    // Filter doctors based on the selected category
                    LaunchedEffect(selectedCategory.value) {
                        val sortedDoctors = doctors
                            .filter { it.specialist == selectedCategory.value }
                            .sortedByDescending { it.userRating }

                        // Update the UI with the filtered doctors
                        viewModelmain.updateDoctorList(sortedDoctors)
                    }

                    // Display the list of doctors for the selected category
                    val filteredDoctors = viewModelmain.filteredDoctors
                    Column(modifier = Modifier.fillMaxSize().padding(top=10.dp)) {
                        Text(
                            text = "Top Rated Doctors",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 15.sp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(modifier = Modifier.padding(15.dp))
                        // DoctorNav(navController) // Consider uncommenting if you have navigation
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(filteredDoctors.value) { doctor ->
                                profileDetailsList(doctor, navController)
                            }
                        }
                    }
                }
            } else {
                navController.popBackStack()
                navController.navigate("searchScreen")
                Toast.makeText(context, "Symptoms not Tracked", Toast.LENGTH_SHORT).show()
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp),
                    color = Color.Blue
                )
            }
        }
    }
}



@Composable
fun CategoryItemsScreen(
    categories: List<String>,
    selectedCategory: MutableState<String>
) {
    LazyColumn {
        item {
            LazyRow {
                items(categories) { category ->
                    CategoryItem(
                        title = category,
                        selected = category == selectedCategory.value,
                        onItemClick = { selectedCategory.value = category }
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CategoryItem(
    title: String,
    selected: Boolean,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
            .clickable(onClick = onItemClick),
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.onSurface),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Color(0xFF00B9E4).copy(alpha = 1f) else MaterialTheme.colorScheme.surface,
            contentColor = if (selected) Color.White else MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp))
        {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profileDetailsList(doctor: Doctor, navController: NavHostController) {

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = {navController.navigate("doctor_details/${doctor.doctorId}")}) {
        Row(modifier = Modifier.padding(6.dp)){
            DocimageList()
            Spacer(modifier = Modifier.padding(6.dp))
            Column() {
                Text(text = "Dr. ${doctor.name}", fontSize = 22.sp, color = Color.Black, modifier = Modifier)
                Text(text = "${doctor.specialist}", fontSize = 15.sp, color = Color.Black, modifier = Modifier)
                Spacer(modifier = Modifier.padding(6.dp))
                Row() {
                    Box(modifier = Modifier.background(Color(0xFF00B9E4))){
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "Rating_Star", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(text = "${doctor.userRating}",color = Color.Black)
                }
            }
        }
    }
}
@Composable
fun DocimageList() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(60.dp),
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
fun DoctorNav(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        title = {
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "menu"
                    ,tint = Color.White
                )
            }
        },
        actions = {
        },
        scrollBehavior = scrollBehavior,
        modifier = Modifier.padding(top = 15.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
    )
}

