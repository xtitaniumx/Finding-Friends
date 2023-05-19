package com.example.peoplefind.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.databinding.ActivityChatBinding
import com.example.peoplefind.domain.model.response.ChatMessage
import com.example.peoplefind.domain.model.response.MeetMessage
import com.example.peoplefind.presentation.adapter.ChatMessageAdapter
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel.Mode.Normal
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel.Mode.Thread
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel.State.NavigateUp
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

class ChatActivity : AppCompatActivity(), ChatMessageAdapter.OnMeetClickListener {
    private lateinit var binding: ActivityChatBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val chatMessageAdapter by lazy { ChatMessageAdapter(this) }

    companion object {
        private const val CID_KEY = "cid_key"

        fun newIntent(context: Context, channel: Channel): Intent =
            Intent(context, ChatActivity::class.java).putExtra(CID_KEY, channel.cid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        /*launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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
        }*/

        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        /*buttonBack.setOnClickListener { finish() }

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
        }*/


        val cid = checkNotNull(intent.getStringExtra(CID_KEY)) {
            "Specifying a channel id is required when starting ChannelActivity"
        }

        // Step 1 - Create three separate ViewModels for the views so it's easy
        //          to customize them individually
        val factory = MessageListViewModelFactory(cid)
        val messageListHeaderViewModel: MessageListHeaderViewModel by viewModels { factory }
        val messageListViewModel: MessageListViewModel by viewModels { factory }
        val messageInputViewModel: MessageInputViewModel by viewModels { factory }

        // TODO set custom Imgur attachment factory

        // Step 2 - Bind the view and ViewModels, they are loosely coupled so it's easy to customize
        messageListHeaderViewModel.bindView(binding.messageListHeaderView, this@ChatActivity)
        messageListViewModel.bindView(binding.messageListView, this@ChatActivity)
        messageInputViewModel.bindView(binding.messageInputView, this@ChatActivity)

        // Step 3 - Let both MessageListHeaderView and MessageInputView know when we open a thread
        messageListViewModel.mode.observe(this@ChatActivity) { mode ->
            when (mode) {
                is Thread -> {
                    messageListHeaderViewModel.setActiveThread(mode.parentMessage)
                    messageInputViewModel.setActiveThread(mode.parentMessage)
                }
                Normal -> {
                    messageListHeaderViewModel.resetThread()
                    messageInputViewModel.resetThread()
                }
            }
        }

        // Step 4 - Let the message input know when we are editing a message
        binding.messageListView.setMessageEditHandler(messageInputViewModel::postMessageToEdit)

        // Step 5 - Handle navigate up state
        messageListViewModel.state.observe(this@ChatActivity) { state ->
            if (state is NavigateUp) {
                finish()
            }
        }

        // Step 6 - Handle back button behaviour correctly when you're in a thread
        val backHandler = {
            messageListViewModel.onEvent(MessageListViewModel.Event.BackButtonPressed)
        }
        binding.messageListHeaderView.setBackButtonClickListener(backHandler)
        onBackPressedDispatcher.addCallback(this@ChatActivity) {
            backHandler()
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