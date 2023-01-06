package com.example.sari_saristorelog.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sari_saristorelog.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val appTypography = androidx.compose.material.Typography(
    h1 = TextStyle(
        fontFamily = Lato.bold,
        fontSize = 30.sp),
    h2 = TextStyle(
        fontFamily = Lato.bold,
        fontSize = 15.sp,
        color = Color.LightGray),
    h3 = TextStyle(
        fontFamily = Inter.bold,
        fontSize = 20.sp),
    h4 = TextStyle(
        fontFamily = Inter.medium,
        fontSize = 20.sp),
    h5 = TextStyle(
        fontFamily = Inter.regular,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = Lato.regular,
        fontSize = 20.sp),

    body2 = TextStyle(
        fontFamily = Inter.light,
        fontSize = 20.sp
    ),
    button = TextStyle(
        fontFamily = Inter.bold,
        fontSize = 30.sp,
        color = Color.White
    )



)