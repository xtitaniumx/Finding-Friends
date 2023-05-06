package com.example.peoplefind.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peoplefind.R
import com.example.peoplefind.databinding.FragmentMessengerBinding
import com.example.peoplefind.domain.model.response.Chat
import com.example.peoplefind.presentation.ChatActivity
import com.example.peoplefind.presentation.adapter.ChatAdapter

class MessengerFragment : Fragment(), ChatAdapter.OnClickListener {
    private lateinit var binding: FragmentMessengerBinding
    private val chatAdapter by lazy { ChatAdapter(this) }

    companion object {
        @JvmStatic
        fun newInstance() = MessengerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMessengerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        listChats.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = chatAdapter
        }

        chatAdapter.submitList(
            listOf(
                Chat("1", "Не Владимир", R.drawable.sample_card, "Привет!"),
                Chat("2", "Совсем не Владимир", R.drawable.sample_card, "Как дела?"),
                Chat("3", "Точно Не Владимир", R.drawable.sample_card, "Hi!")
            )
        )


    }

    override fun onChatClick(item: Chat) {
        val intent = Intent(requireActivity(), ChatActivity::class.java).apply {
            putExtra("ChatUser", item.userName)
            putExtra("ChatUserImage", item.userImage)
        }
        startActivity(intent)
    }
}