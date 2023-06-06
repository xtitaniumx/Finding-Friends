package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityMainBinding
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.presentation.fragment.HomeFragment
import com.example.peoplefind.presentation.fragment.MessengerFragment
import com.example.peoplefind.presentation.fragment.ProfileFragment
import com.example.peoplefind.presentation.vm.MessengerViewModel
import com.example.peoplefind.presentation.vm.QuestionnaireViewModel
import io.getstream.chat.android.client.ChatClient
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val messengerViewModel by viewModel<MessengerViewModel>()
    private val questionnaireViewModel by viewModel<QuestionnaireViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initStreamChat()
        initFragments()
        setContentView(binding.root)
    }

    private fun initStreamChat() {
        messengerViewModel.getUserId()
        messengerViewModel.userId.observe(this) {
            questionnaireViewModel.getQuestionnaire(it)
        }

        questionnaireViewModel.questionnaire.observe(this) { result ->
            result.onSuccess {
                if (it == null) return@onSuccess
                messengerViewModel.getStreamChatUser(it.userId, it.name)
            }
        }

        messengerViewModel.streamChatUser.observe(this) {
            val client = ChatClient.instance()
            val streamChatUser = it.first

            client.connectUser(
                user = streamChatUser,
                token = it.second
            ).enqueue()
        }
    }

    private fun initFragments() = with(binding) {
        navMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> {
                    openFragment(HomeFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
                R.id.menuChat -> {
                    openFragment(MessengerFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
                R.id.menuProfile -> {
                    openFragment(ProfileFragment.getInstance())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, fragment)
            .commit()
    }
}