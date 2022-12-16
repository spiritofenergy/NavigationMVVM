package com.kodex.navigationmvvm.navigation

import android.app.Application
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kodex.navigationmvvm.Event
import com.kodex.navigationmvvm.MainActivity
import com.kodex.navigationmvvm.R
import com.kodex.navigationmvvm.screens.base.BaseScreen

const val ARG_SCREEN = "SCREEN"
class MainNavigator(application: Application): AndroidViewModel(application), Navigator {

    val whenActivityActive = MainActivityActions()

    private val _result = MutableLiveData<Event<Any>>()
    val result: LiveData<Event<Any>> = _result

    override fun launch(screen: BaseScreen) = whenActivityActive {
        launchFragment(it, screen)
    }

    override fun goBack(result: Any?) = whenActivityActive {
        if (result != null){
            _result.value =Event(result)
        }
        it.onBackPressed()
     }

    override fun onCleared() {
        super.onCleared()
        whenActivityActive.clear()
    }
    override fun toast(messageRes: Int) {
        Toast.makeText(getApplication(), messageRes, Toast.LENGTH_LONG).show()
    }

    override fun getString(messageRes: Int): String {
        return getApplication<Application>().getString(messageRes)
    }
    fun launchFragment(activity: MainActivity, screen: BaseScreen, addToBackStack: Boolean = true){
        val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment
        fragment.arguments = bundleOf(ARG_SCREEN to screen)
        val transition = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack)transition.addToBackStack(null)
        transition
            .replace(R.id.fragmentContainer, fragment)
            .commit()

    }
}

