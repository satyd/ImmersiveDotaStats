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
    val heroViewEntityList = remember { mutableStateListOf<HeroInfoViewEntity>() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            heroViewEntityList.addAll(viewModel.getHeroInfo())
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
