package com.levp.immersivedotastats.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.lifecycle.ViewModelStoreOwner

fun Context.getViewModelStoreOwner(): ViewModelStoreOwner? = when (this) {
    is ViewModelStoreOwner -> this
    is ContextWrapper -> baseContext.getViewModelStoreOwner()
    else -> null
}