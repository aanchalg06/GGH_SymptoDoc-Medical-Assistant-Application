package com.aanchal.symptodoc.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.aanchal.symptodoc.presentation.Dimens.MediumPadding1
import com.aanchal.symptodoc.presentation.Dimens.MediumPadding2
import com.aanchal.symptodoc.presentation.onboarding.Page

@Composable
fun OnBoardingPage(
    page: Page
)
{
    val textColor = MaterialTheme.colors.onBackground

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f),
            painter= painterResource(id = page.image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.title,
            modifier= Modifier.padding(horizontal = MediumPadding2),
            color = textColor, // Use textColor instead of hardcoding Color.Black
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
        Text(
            text = page.description,
            modifier= Modifier.padding(horizontal = MediumPadding2),
            color = textColor, // Use textColor instead of hardcoding Color.Black
            textAlign = TextAlign.Center
        )
    }
}


//
//@Preview(showBackground = true)
//@Preview(uiMode = UI_MODE_NIGHT_YES,showBackground = true)
//@Composable
//fun OnBoardingPagePreview() {
//    SymptomAppTheme {
//        OnBoardingPage(modifier = Modifier, page =pages[0] )
//    }
//}