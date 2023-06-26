package com.levp.immersivedotastats.presentation.userinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@Composable
fun UserInfoScreen(
    viewModel: UserInfoViewModel = singleViewModel(),
    modifier: Modifier = Modifier
) {
    val imageUrl by remember { mutableStateOf(viewModel.imageUrl) }
    var text by rememberSaveable { mutableStateOf("") }
    val playerInfo by remember { mutableStateOf(viewModel.playerInfo) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Label") }
        )
        Button(onClick = {
            viewModel.loadUserInfo()
        }) {
            Text(text = "Get User Data")
        }
        Text(
            text = viewModel.getUserInfo(),
            modifier = modifier
        )
    }
}