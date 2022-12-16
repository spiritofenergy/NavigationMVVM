package com.kodex.navigationmvvm.screens.edit



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kodex.navigationmvvm.databinding.FragmentEditBinding
import com.kodex.navigationmvvm.databinding.FragmentHelloBinding
import com.kodex.navigationmvvm.screens.base.BaseFragment
import com.kodex.navigationmvvm.screens.base.BaseScreen
import com.kodex.navigationmvvm.screens.base.screenViewModel

class EditFragment: BaseFragment() {
    class Screen(
        val initialValue: String
    ) : BaseScreen

    override val viewModel by screenViewModel<EditViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentEditBinding.inflate(inflater, container, false)

        viewModel.initialMessageEvent.observe(viewLifecycleOwner) {
            it.getValue()?.let { message -> binding.valueEditText.setText(message) }
        }

        binding.saveButton.setOnClickListener {
            viewModel.onSavePressed(binding.valueEditText.text.toString())
        }
        binding.cancelButton.setOnClickListener {
            viewModel.onCanselPressed()
        }
        return binding.root
    }
}
