package com.levp.immersivedotastats.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified VM : ViewModel> singleViewModel(
    key: String? = null,
    factory: ViewModelProvider.Factory? = null
): VM = viewModel(
    VM::class.java,
    LocalContext.current.getViewModelStoreOwner()
        ?: throw NullPointerException("ViewModelStoreOwner is null"),
    key,
    factory
)