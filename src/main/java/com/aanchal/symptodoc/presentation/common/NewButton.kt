package com.aanchal.symptodoc.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewButton(
    text: String,
    onClick:() -> Unit
) {
    Button(onClick=onClick,colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ),
        shape = RoundedCornerShape(size = 6.dp)
    ){

    }
  }

@Composable
fun NewTextButotn(
    text: String,
    onClick: () -> Unit
) {
//    TextButton(onClick = onClick) {
//        Text(text,
//        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
//            color = Color.White
//        )
//    }

//    Button(
//        onClick = { onClick() },
//        shape = RoundedCornerShape(20.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 20.dp, end = 20.dp),
//        colors = ButtonDefaults.buttonColors(Color(0xFF04132D),Color.White),
//        content = {
//            Row {
//                Text(text = "$text", color = Color.White)
//                Icon(
//                    imageVector = Icons.Filled.KeyboardArrowRight,
//                    contentDescription = "rightArrow",
//                    tint = Color.White
//                )
//            }
//        }
//    )
    OutlinedButton(
        onClick = { onClick() },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(Color(0xFF08007C)),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.padding(bottom = 20.dp),
        content = {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "open_slider",
                tint = Color.White
            )
        }
    )
}