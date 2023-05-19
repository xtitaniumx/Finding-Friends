package com.example.peoplefind.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.peoplefind.databinding.FragmentMessengerBinding
import com.example.peoplefind.presentation.ChatActivity
import com.example.peoplefind.presentation.vm.MessengerViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessengerFragment : Fragment() {
    private val messengerViewModel by viewModel<MessengerViewModel>()
    private lateinit var binding: FragmentMessengerBinding

    companion object {
        @JvmStatic
        fun newInstance() = MessengerFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messengerViewModel.getStreamChatUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessengerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        // Step 3 - Authenticate and connect the user
        messengerViewModel.streamChatUser.observe(viewLifecycleOwner) {
            val client = ChatClient.instance()
            val streamChatUser = it.first

            client.connectUser(
                user = streamChatUser,
                token = it.second
            ).enqueue()

            // Step 4 - Set the channel list filter and order
            // This can be read as requiring only channels whose "type" is "messaging" AND
            // whose "members" include our "user.id"
            val filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.`in`("members", listOf(streamChatUser.id))
            )
            val viewModelFactory =
                ChannelListViewModelFactory(filter, ChannelListViewModel.DEFAULT_SORT)
            val viewModel: ChannelListViewModel by viewModels { viewModelFactory }

            // Step 5 - Connect the ChannelListViewModel to the ChannelListView, loose
            //          coupling makes it easy to customize
            viewModel.bindView(binding.channelListView, viewLifecycleOwner)
        }

        binding.channelListView.setChannelItemClickListener { channel ->
            startActivity(ChatActivity.newIntent(requireActivity(), channel))
        }
    }
}