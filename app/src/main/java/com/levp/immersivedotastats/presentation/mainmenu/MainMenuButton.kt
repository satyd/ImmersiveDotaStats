package com.levp.immersivedotastats.presentation.mainmenu

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuButton(onClick: () -> Unit, text: String) {
    Button(
        modifier = Modifier.size(256.dp, 56.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}