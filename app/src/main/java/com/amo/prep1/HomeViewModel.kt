package com.amo.prep1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private  val _message = MutableStateFlow("")
    val message : StateFlow<String>
        get() = _message

    fun loadMessage() {
        viewModelScope.launch{
            _message.value = "Greetings!"
        }
    }

}