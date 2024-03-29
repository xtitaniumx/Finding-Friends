package com.example.peoplefind.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.peoplefind.databinding.BottomSheetInterestBinding
import com.example.peoplefind.presentation.vm.InterestViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val ARG_INTEREST = "interest_name"

class InterestBottomSheet : BottomSheetDialogFragment() {
    private val interestViewModel by activityViewModels<InterestViewModel>()
    private lateinit var binding: BottomSheetInterestBinding
    private var interestName: String? = null

    companion object {
        const val TAG = "interest_bottom_sheet"

        fun newInstance(interestName: String) = InterestBottomSheet().apply {
            arguments = Bundle().apply {
                putString(ARG_INTEREST, interestName)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            interestName = it.getString(ARG_INTEREST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetInterestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        textInterestName.text = interestName

        buttonConfirmDescription.setOnClickListener {
            interestName?.let { name ->
                interestViewModel.setInterest(name, editTextInterestDescription.text.toString())
            }
            dismiss()
        }
    }
}