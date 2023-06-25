package com.levp.immersivedotastats.presentation

import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.levp.immersivedotastats.domain.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun PlayerInfoScreen(modifier: Modifier = Modifier) {
    var text by rememberSaveable { mutableStateOf("350885037") }
    var playerInfo by remember { mutableStateOf("there have to be player info") }
    var imageUrl by remember { mutableStateOf("https://www.example.com/image.jpg") }

    val coroutineScope = rememberCoroutineScope()

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
            coroutineScope.launch {
                val response = try {
                    Log.i("hehe","trying to get response with $text")
                    RetrofitInstance.playerApi.getPlayerById(text.toInt())
                } catch (e: IOException) {
                    Log.e("hehe", "IOException")
                    return@launch
                } catch (e: HttpException) {
                    Log.e("hehe", "no Internet")
                    return@launch
                }
                if(response.isSuccessful && response.body() != null){
                    playerInfo = response.body()!!.toString()
                    imageUrl = response.body()!!.profile.avatarFull
                } else {
                    Log.w("hehe","Response was not successful!!! code = ${response.code()}")
                }
            }
        }) {
            Text(text = "Get Player Data")
        }
        Text(
            text = playerInfo,
            modifier = modifier
        )
    }
}