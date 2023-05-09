package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peoplefind.databinding.ActivityChatBinding
import com.example.peoplefind.domain.model.response.ChatMessage
import com.example.peoplefind.domain.model.response.DateMessage
import com.example.peoplefind.domain.model.response.MeetMessage
import com.example.peoplefind.domain.model.response.Message
import com.example.peoplefind.presentation.adapter.ChatMessageAdapter

class ChatActivity : AppCompatActivity(), ChatMessageAdapter.OnMeetClickListener {
    private lateinit var binding: ActivityChatBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val chatMessageAdapter by lazy { ChatMessageAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val list = chatMessageAdapter.currentList
                chatMessageAdapter.submitList(
                    list.plus(
                        MeetMessage(
                            owner = 1,
                            inviteUser = "Приглашает " + binding.textChatUser.text.toString(),
                            date = result.data?.getStringExtra("MeetDate").toString(),
                            place = result.data?.getStringExtra("MeetPlace").toString(),
                            goal = result.data?.getStringExtra("MeetGoal").toString(),
                            time = "00:00"
                        )
                    )
                )
            }
        }

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
                Message(owner = 1, "Как дела?", "15:25"),
                MeetMessage(owner = 0, inviteUser = "Приглашает " + textChatUser.text.toString(), "На 25.06.2023", "Ул. Кирова, 25", "Разговоры о важном", "00:00")
            )
        )

        buttonMeet.setOnClickListener {
            val intent = Intent(this@ChatActivity, MeetActivity::class.java)
            launcher.launch(intent)
        }

        buttonSendMessage.setOnClickListener {
            if (editTextMessage.text.isEmpty()) return@setOnClickListener

            val list = chatMessageAdapter.currentList
            chatMessageAdapter.submitList(
                list.plus(Message(owner = 1, editTextMessage.text.toString(), "00:00"))
            )
            listMessages.smoothScrollToPosition(chatMessageAdapter.itemCount - 1)
        }
    }

    override fun onMeetAcceptClick() {

    }

    override fun onMeetRejectClick(item: MeetMessage) {
        val list = chatMessageAdapter.currentList.toMutableList().apply {
            remove(item)
        }
        val newList = ArrayList<ChatMessage>()
        chatMessageAdapter.submitList(newList.plus(list))
    }
}