package com.bangkit.scrapncraft.ui.main.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No Data Available"
    }
    val text: LiveData<String> = _text
}