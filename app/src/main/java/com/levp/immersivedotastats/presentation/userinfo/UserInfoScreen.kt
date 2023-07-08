package com.levp.immersivedotastats.presentation.userinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.levp.immersivedotastats.App
import com.levp.immersivedotastats.domain.network.dto.HeroPerformanceStat
import com.levp.immersivedotastats.presentation.common.HeroStatEntry
import com.levp.immersivedotastats.utils.extensions.singleViewModel

@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: UserInfoViewModel = singleViewModel(),
) {
    val userInfoState by viewModel.uiState.collectAsState()
    var userId by rememberSaveable { mutableStateOf("350885037") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UserInfoHeader(userInfoState)
        Spacer(modifier = Modifier.height(4.dp))
        if (userInfoState.userHeroesPerformance.size > 5) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(
                    userInfoState.userHeroesPerformance.subList(0, 6)
                ) { index: Int, item: HeroPerformanceStat ->
                    HeroStatEntry(item)
                }
            }
        }
        TextField(
            value = userId,
            onValueChange = {
                userId = it
            },
            label = { Text("Search player by ID") }
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = {
            viewModel.loadUserInfoStratz(userId)
        }) {
            Text(text = "Get User Data")
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (userInfoState.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = userInfoState.userInfo.toString().replace(", ", "\n"))
        }
    }
}


