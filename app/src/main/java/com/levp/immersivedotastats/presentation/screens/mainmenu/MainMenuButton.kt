package com.levp.immersivedotastats.presentation.screens.mainmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levp.immersivedotastats.presentation.theme.StatsTheme

@Composable
fun MainMenuButton(onClick: () -> Unit, text: String) {
    Button(
        modifier = Modifier
            .size(256.dp, 56.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = StatsTheme.colors.mainButton,
            contentColor = StatsTheme.colors.mainTextLight
        )
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}