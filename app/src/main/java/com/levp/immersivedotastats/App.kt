package com.levp.immersivedotastats

import android.app.Application
import com.levp.immersivedotastats.core.PreferencesManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application(){
    companion object {
        lateinit var instance: App
            private set
    }

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}
