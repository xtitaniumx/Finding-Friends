package com.example.peoplefind.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.peoplefind.databinding.FragmentProfileBinding
import com.example.peoplefind.presentation.ChangeQuestionnaireActivity

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        buttonChangeQuestionnaire.setOnClickListener {
            val intent = Intent(requireActivity(), ChangeQuestionnaireActivity::class.java)
            startActivity(intent)
        }
    }
}