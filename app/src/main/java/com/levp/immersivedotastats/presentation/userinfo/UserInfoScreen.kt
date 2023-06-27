package com.levp.immersivedotastats.presentation.userinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.levp.immersivedotastats.App
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: UserInfoViewModel = singleViewModel(),
) {
    val userInfoState by viewModel.uiState.collectAsState()
    var text by rememberSaveable { mutableStateOf("350885037") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if(App.instance.preferencesManager.getBoolean(UserInfoViewModel.IS_USER_ID_SAVED)) {
            UserInfoHeader(userInfoState)
        }
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Label") }
        )
        Button(onClick = {
            viewModel.loadUserInfo(text)
        }) {
            Text(text = "Get User Data")
        }
    }
}


