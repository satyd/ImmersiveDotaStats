package com.levp.immersivedotastats.presentation.heroesinfo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.levp.immersivedotastats.domain.network.RetrofitInstance
import com.levp.immersivedotastats.utils.HeroInfoMapper
import com.levp.immersivedotastats.utils.extensions.singleViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun HeroesInfoScreen(
    viewModel: HeroesInfoViewModel = singleViewModel(),
    modifier: Modifier = Modifier
) {
    val mapper = HeroInfoMapper()
    val heroViewEntityList = remember { mutableStateListOf<HeroInfoViewEntity>() }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            coroutineScope.launch {
                val response = try {
                    Log.i("hehe", "trying to get heroes response")
                    RetrofitInstance.heroApi.getHeroStatsInfo()
                } catch (e: IOException) {
                    Log.e("hehe", "IOException")
                    return@launch
                } catch (e: HttpException) {
                    Log.e("hehe", "no Internet")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    val list = response.body()!!
                    heroViewEntityList.addAll(mapper.mapList(list))
                } else {
                    Log.w("hehe", "Response was not successful!!! code = ${response.code()}")
                }
            }
        }) {
            Text(text = "get heroes")
        }
        Spacer(modifier = Modifier.height(6.dp))
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            itemsIndexed(
                items = heroViewEntityList,
                key = { _, item -> item.id }) { index, item ->
                HeroesInfoEntryItem(viewEntity = item)
                if (index != heroViewEntityList.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}