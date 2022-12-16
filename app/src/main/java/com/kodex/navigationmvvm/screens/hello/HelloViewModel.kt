package com.kodex.navigationmvvm.screens.hello

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kodex.navigationmvvm.R
import com.kodex.navigationmvvm.navigation.Navigator
import com.kodex.navigationmvvm.screens.base.BaseScreen
import com.kodex.navigationmvvm.screens.base.BaseViewModel
import com.kodex.navigationmvvm.screens.edit.EditFragment

class HelloViewModel(
    private val navigator: Navigator,
    screen: HelloFragment.Screen
) : BaseViewModel(){

    private val _currentMessageLiveData = MutableLiveData<String>()
    val currentMessageLiveData: LiveData<String> = _currentMessageLiveData

    init {
        _currentMessageLiveData.value = navigator.getString(R.string.hello_world)
    }

    override fun onResult(result: Any) {
        if (result is String){
            _currentMessageLiveData.value = result
        }
    }
    fun onEditPressed(){
        navigator.launch(EditFragment.Screen(initialValue = currentMessageLiveData.value!!))
    }
}