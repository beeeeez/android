package com.example.androidfinal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class teamModel : ViewModel() {

    private val _result = MutableLiveData<String>()
 //   internal var result: `val`? = null
    internal fun get(): MutableLiveData<String>{return _result}

    init {
        _result.value = "Hello, world!"
    }

}
