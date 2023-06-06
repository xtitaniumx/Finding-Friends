package com.example.peoplefind.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.peoplefind.databinding.FragmentMessengerBinding
import com.example.peoplefind.presentation.ChatActivity
import com.example.peoplefind.presentation.ChatSearchActivity
import com.example.peoplefind.presentation.vm.MessengerViewModel
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MessengerFragment : Fragment() {
    private val messengerViewModel by activityViewModel<MessengerViewModel>()
    private lateinit var binding: FragmentMessengerBinding

    companion object {
        @JvmStatic
        fun newInstance() = MessengerFragment()
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
        buttonSearch.setOnClickListener {
            val intent = Intent(requireActivity(), ChatSearchActivity::class.java)
            startActivity(intent)
        }

        messengerViewModel.streamChatUser.observe(viewLifecycleOwner) {
            val streamChatUser = it.first

            val filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.`in`("members", listOf(streamChatUser.id))
            )

            val viewModelFactory = ChannelListViewModelFactory(filter, ChannelListViewModel.DEFAULT_SORT)
            val viewModel: ChannelListViewModel by viewModels { viewModelFactory }
            viewModel.bindView(binding.channelListView, viewLifecycleOwner)
        }

        messengerViewModel.channel.observe(viewLifecycleOwner) {
            //channelListView.setChannels(listOf(ChannelListItem.ChannelItem(it)))
        }

        channelListView.setChannelItemClickListener { channel ->
            startActivity(ChatActivity.newIntent(requireActivity(), channel.cid))
        }
    }
}