package com.example.peoplefind.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.peoplefind.databinding.ActivityChatSearchBinding
import io.getstream.chat.android.ui.search.list.viewmodel.SearchViewModel
import io.getstream.chat.android.ui.search.list.viewmodel.bindView

class ChatSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatSearchBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        val searchViewModel: SearchViewModel by viewModels()
        searchViewModel.bindView(searchResultView, this@ChatSearchActivity)

        searchInputView.setSearchStartedListener { query ->
            searchViewModel.setQuery(query)
        }

        searchResultView.setSearchResultSelectedListener { message ->
            startActivity(ChatActivity.newIntent(this@ChatSearchActivity, message.cid))
        }
    }
}