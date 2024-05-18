package ru.aidar.common.utils

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import ru.aidar.common.utils.JadroAppStyle.WhiteBlue

object Dimens {

    val commonSpanStyle = SpanStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = JadroAppStyle.JadroFontFamily
    )

    val whiteBlueCommonSpanStyle = SpanStyle(
        color = WhiteBlue,
        fontWeight = FontWeight.Bold,
        fontFamily = JadroAppStyle.JadroFontFamily,
    )

    const val fontSize12 = 12
    const val fontSize15 = 15
    const val fontSize18 = 18
    const val fontSize20 = 20
    const val fontSize22 = 22
    const val fontSize28 = 28
    const val fontSize52 = 52

    const val size34 = 34
    const val size70 = 70

    const val height56 = 56
    const val height105 = 105
    const val height150 = 150

    const val padding5 = 5
    const val padding7 = 7
    const val padding10 = 10
    const val padding14 = 14
    const val padding18 = 18
    const val padding25 = 25
    const val padding28 = 28
    const val padding30 = 30
    const val padding40 = 40

    const val corner35 = 35

    const val width2 = 2
    const val width4 = 4

    const val letterSpacing2 = 2
}