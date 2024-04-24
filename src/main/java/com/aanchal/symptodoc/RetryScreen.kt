package com.aanchal.symptodoc

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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.aanchal.symptodoc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetryScreen(
    navController: NavHostController
) {
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
                painter = painterResource(id = R.drawable.nosignal_img),
                contentDescription = "Welcome Image",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Turn on the Internet",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(20.dp),
                color = MaterialTheme.colorScheme.background
            )

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
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "leftArrow",
                            tint = Color.White
                        )
                        Text(text = "Retry", color = Color.White)

                    }
                }
            )
        }

    }

}

//@Preview
//@Composable
// fun ggg() {
//     var navController=rememberNavController()
//    RetryScreen(navController)
//}