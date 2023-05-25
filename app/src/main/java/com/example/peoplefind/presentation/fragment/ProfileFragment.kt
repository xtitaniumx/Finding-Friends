package com.example.peoplefind.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.peoplefind.databinding.FragmentProfileBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.presentation.ChangeQuestionnaireActivity
import com.example.peoplefind.presentation.LoginActivity
import com.example.peoplefind.presentation.util.clearStack
import com.example.peoplefind.presentation.util.showErrorDialog
import com.example.peoplefind.presentation.vm.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private val profileViewModel by viewModel<ProfileViewModel>()
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

        buttonLogout.setOnClickListener {
            profileViewModel.logoutAccount()
        }

        profileViewModel.logoutResult.observe(viewLifecycleOwner) { result ->
            result.onLoading {
                veilLogout.veil()
            }.onSuccess {
                logout()
            }.onFailure { message, error ->
                logout()
            }
        }
    }

    private fun logout() {
        profileViewModel.deleteUserData()
        binding.veilLogout.unVeil()
        val intent = Intent(requireActivity(), LoginActivity::class.java)
            .apply { clearStack() }
        startActivity(intent)

        showErrorDialog(
            title = "Истекло время авторизации",
            message = "Вы будете перенаправлены на страницу входа в аккаунт"
        ) {
            val intent1 = Intent(requireActivity(), LoginActivity::class.java)
                .apply { clearStack() }
            startActivity(intent1)
        }
    }
}