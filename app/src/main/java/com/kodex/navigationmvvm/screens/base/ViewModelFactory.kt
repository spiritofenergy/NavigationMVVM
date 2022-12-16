package com.kodex.navigationmvvm.screens.base

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodex.navigationmvvm.navigation.ARG_SCREEN
import com.kodex.navigationmvvm.navigation.MainNavigator
import com.kodex.navigationmvvm.navigation.Navigator

class ViewModelFactory(
    private val screen: BaseScreen,
    private val fragment: BaseFragment
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val hostActivity = fragment.requireActivity()
        val application = hostActivity.application
        val navigatorProvider = ViewModelProvider(hostActivity, ViewModelProvider.AndroidViewModelFactory(application))
        val navigator = navigatorProvider[MainNavigator::class.java]

        val constructor = modelClass.getConstructor(Navigator::class.java, screen::class.java)
        return constructor.newInstance(navigator, screen)
    }

}
inline fun <reified VM : ViewModel> BaseFragment.screenViewModel() = viewModels<VM> {
    val screen = requireArguments().getSerializable(ARG_SCREEN) as BaseScreen
    ViewModelFactory(screen, this)
}