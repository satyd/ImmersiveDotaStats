package com.levp.immersivedotastats.core

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(IMMERSIVE_STATS_PREFERENCES, 0)
    }

    companion object {
        private const val IMMERSIVE_STATS_PREFERENCES = "DOTA_IMMERSIVE_STATS_PREFS"
    }

    fun saveString(key: String, value: String) =
        preferences.edit().putString(key, value).apply()

    fun getString(key: String): String =
        preferences.getString(key, "").orEmpty()

    fun saveInt(key: String, value: Int) =
        preferences.edit().putInt(key, value).apply()

    fun getInt(key: String, defValue: Int = 0) =
        preferences.getInt(key, defValue)

    fun saveBoolean(key: String, value: Boolean) =
        preferences.edit().putBoolean(key, value).apply()

    fun getBoolean(key: String) =
        preferences.getBoolean(key, false)
}