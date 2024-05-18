package ru.aidar.common.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.aidar.common.R

object JadroAppStyle {


    val JadroFontFamily = FontFamily(
        Font(R.font.bold, FontWeight.Bold)
    )

    val WhiteBlue = Color(0xFFE0FFFF)

    private val linearGradientFirst = Color(0xFF5d51b7) // todo
    private val linearGradientSecond = Color(0xFF7b71c4)
    private val linearGradientThird = Color(0xFFd4d0ee)
    private val linearGradientFourth = Color(0xFF9289cf)
    private val linearGradientFifth = Color(0xFF4b3db1)

    val CardColor2 = Color(0xFFa9a3d9)

    val backGroundGradient = Brush.linearGradient(
        listOf(
            linearGradientFirst,
            linearGradientFirst,
            linearGradientSecond,
            linearGradientThird,
            linearGradientFourth,
            linearGradientFifth,
            linearGradientFifth,
            linearGradientFifth,
        )
    )

}