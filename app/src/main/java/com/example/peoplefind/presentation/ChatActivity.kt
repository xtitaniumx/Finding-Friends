package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peoplefind.databinding.ActivityChatBinding
import com.example.peoplefind.domain.model.response.DateMessage
import com.example.peoplefind.domain.model.response.Message
import com.example.peoplefind.presentation.adapter.ChatMessageAdapter

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val chatMessageAdapter by lazy { ChatMessageAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        buttonBack.setOnClickListener { finish() }

        textChatUser.text = intent.getStringExtra("ChatUser")
        imageChatUser.setImageResource(intent.getIntExtra("ChatUserImage", 0))

        listMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = chatMessageAdapter
        }

        chatMessageAdapter.submitList(
            listOf(
                DateMessage("Сегодня"),
                Message(owner = 0, "Привет!", "15:20"),
                Message(owner = 1, "Как дела?", "15:25")
            )
        )

        buttonSendMessage.setOnClickListener {
            if (editTextMessage.text.isEmpty()) return@setOnClickListener

            val list = chatMessageAdapter.currentList
            chatMessageAdapter.submitList(
                list.plus(Message(owner = 1, editTextMessage.text.toString(), "00:00"))
            )
            listMessages.smoothScrollToPosition(chatMessageAdapter.itemCount - 1)
        }
    }
}