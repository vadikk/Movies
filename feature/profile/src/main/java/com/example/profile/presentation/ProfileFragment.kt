package com.example.profile.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.domain.utils.hideKeyBoard
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


internal class ProfileFragment : Fragment() {

    private val profileVM: ProfileVM by viewModel()
    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileVM.profileState.collect { updateUI(it) }
            }
        }

        binding?.firstNameEditText?.doAfterTextChanged {
            profileVM.changeFirstName(it.toString())
        }
        binding?.lastNameEditText?.doAfterTextChanged {
            profileVM.changeLastName(it.toString())
        }
        binding?.saveBtn?.setOnClickListener { profileVM.saveChanges() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun updateUI(state: ProfileState){
        binding?.saveBtn?.isVisible = state.isApplyBtnEnabled
        if (!state.isApplyBtnEnabled) resetFocus()

        binding?.firstNameEditText?.setText(state.profile.firstName)
        binding?.lastNameEditText?.setText(state.profile.lastName)
        binding?.firstNameEditText?.setSelection(binding?.firstNameEditText?.text?.length ?: 0)
        binding?.lastNameEditText?.setSelection(binding?.lastNameEditText?.text?.length ?: 0)
    }

    private fun resetFocus() {
        if (binding?.firstNameEditText?.isFocused == true) {
            binding?.firstNameEditText?.clearFocus()
            hideKeyBoard(context, binding?.firstNameEditText)
        } else if (binding?.lastNameEditText?.isFocused == true) {
            binding?.lastNameEditText?.clearFocus()
            hideKeyBoard(context, binding?.lastNameEditText)
        }
    }
}