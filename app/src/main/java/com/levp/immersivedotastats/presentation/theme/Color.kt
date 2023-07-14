package com.levp.immersivedotastats.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val CreamBrownColors = StatsThemeColors(
    mainBackground = Color(0xAAEEE3CB),
    mainButton = Color(0xFF9BABB8),
    mainText = Color.Black,
    mainTextLight = Color.White,
    subText = Color.DarkGray,
    headerBackground = Color(0xFF967E76),
    panelBackground = Color(0xFFD7C0AE),
    entryBackground = Color(0xFFD7C0AE),
    winStat = Color(0xFFA9CF54),
    loseStat = Color(0xFFEA030E)
)

val MaterialColdColors = StatsThemeColors(
    mainBackground = Color(0xAAEEEEFB),
    mainButton = Color(0xFF6650A4),
    mainText = Color.Black,
    mainTextLight = Color.White,
    subText = Color.DarkGray,
    headerBackground = Color(0xFF3366A9),
    panelBackground = Color(0xFFD7C0AE),
    entryBackground = Color(0xFFD7C0AE),
    winStat = Color(0xFFA9CF54),
    loseStat = Color(0xFFEA030E)
)
private fun StatsThemeColors(
    mainBackground: Color,
    mainButton: Color,
    mainText: Color,
    mainTextLight: Color,
    subText: Color,
    headerBackground: Color,
    panelBackground: Color,
    entryBackground: Color,
    winStat: Color,
    loseStat: Color
): StatsColors =
    StatsColors(
        mainBackground = mainBackground,
        mainButton = mainButton,
        mainText = mainText,
        mainTextLight = mainTextLight,
        subText = subText,
        headerBackground = headerBackground,
        panelBackground = panelBackground,
        entryBackground = entryBackground,
        winStat = winStat,
        loseStat = loseStat
    )

@Immutable
data class StatsColors(
    val mainBackground: Color = Color.Unspecified,
    val mainButton: Color = Color.Unspecified,
    val mainText: Color = Color.Unspecified,
    val mainTextLight: Color = Color.Unspecified,
    val subText: Color = Color.Unspecified,
    val headerBackground: Color = Color.Unspecified,
    val panelBackground: Color = Color.Unspecified,
    val entryBackground: Color = Color.Unspecified,
    val winStat: Color = Color.Unspecified,
    val loseStat: Color = Color.Unspecified,
)