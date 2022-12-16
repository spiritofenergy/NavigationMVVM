package com.kodex.navigationmvvm.screens.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kodex.navigationmvvm.Event
import com.kodex.navigationmvvm.R
import com.kodex.navigationmvvm.navigation.Navigator
import com.kodex.navigationmvvm.screens.base.BaseViewModel
import com.kodex.navigationmvvm.screens.edit.EditFragment.Screen

class EditViewModel(
    private val navigator: Navigator,
    screen: Screen
): BaseViewModel() {

    private val _initialMessageEvent = MutableLiveData<Event<String>>()
    val initialMessageEvent: LiveData<Event<String>> = _initialMessageEvent

    init {
        _initialMessageEvent.value = Event(screen.initialValue)
    }

    fun onSavePressed(message: String){
        if(message.isBlank()){
            navigator.toast(R.string.empty_message)
        }
        navigator.goBack(message)

    }
    fun onCanselPressed(){
        navigator.goBack()

    }

}