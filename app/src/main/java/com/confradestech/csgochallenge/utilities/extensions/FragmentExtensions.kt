package com.confradestech.csgochallenge.utilities.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

fun <T : ViewModel> Fragment.obtainViewModel(owner: ViewModelStoreOwner,
                                             viewModelClass: Class<T>,
                                             viewmodelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(owner, viewmodelFactory)[viewModelClass]