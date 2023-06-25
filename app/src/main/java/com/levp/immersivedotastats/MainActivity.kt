package com.levp.immersivedotastats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.levp.immersivedotastats.ui.theme.ImmersiveDotaStatsTheme
import com.levp.immersivedotastats.presentation.PlayerInfoScreen
import com.levp.immersivedotastats.presentation.heroesinfo.HeroesInfoScreen
import com.levp.immersivedotastats.presentation.heroesinfo.HeroesInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private val heroInfoViewModel: HeroesInfoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImmersiveDotaStatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroesInfoScreen()
                    //PlayerInfoScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImmersiveDotaStatsTheme {
        PlayerInfoScreen()
    }
}