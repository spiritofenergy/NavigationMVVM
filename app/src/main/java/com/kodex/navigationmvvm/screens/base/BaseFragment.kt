package com.kodex.navigationmvvm.screens.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.kodex.navigationmvvm.R


abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel

}