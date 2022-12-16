package com.kodex.navigationmvvm.navigation

import com.kodex.navigationmvvm.MainActivity

typealias MainActivityAction = (MainActivity)-> Unit

class MainActivityActions {

    var mainActivity: MainActivity? = null
        set(activity) {
            field = activity
            if (activity != null) {
                actions.forEach { it (activity) }
                actions.clear()
            }
        }

    private val actions = mutableListOf<MainActivityAction>()

    operator fun invoke(action: MainActivityAction){
        val activity = this.mainActivity
        if (activity == null){
            actions += action
        }else{
            action(activity)
        }
    }
    fun clear(){
        actions.clear()
    }
}

   /* var mainActivity: MainActivity? = null
        set(activity) {
            field = activity
            if (activity != null) {
                actions.forEach { it(activity) }
                actions.clear()
            }
        }

    private val actions = mutableListOf<MainActivityAction>()
*/


