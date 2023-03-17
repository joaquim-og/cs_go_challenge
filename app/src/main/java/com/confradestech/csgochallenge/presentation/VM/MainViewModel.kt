package com.confradestech.csgochallenge.presentation.VM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchMatchesData()
    }

    private fun fetchMatchesData() {
        viewModelScope.launch{
            //TODO
            // fetch server data
            delay(3000)
            _isLoading.value = true
        }
    }

}