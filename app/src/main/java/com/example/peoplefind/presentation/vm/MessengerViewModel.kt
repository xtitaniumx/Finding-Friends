package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.usecase.GetTokenUseCase
import com.example.peoplefind.domain.usecase.GetUserIdUseCase
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessengerViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getStreamChatTokenUseCase: GetTokenUseCase.GetStreamChatToken
) : ViewModel() {
    private val streamChatUserMutable = MutableLiveData<Pair<User, String>>()
    val streamChatUser: LiveData<Pair<User, String>> = streamChatUserMutable

    fun getStreamChatUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserIdUseCase().collect { userId ->
                userId?.let {
                    val user = User(
                        id = it,
                        name = "Bender",
                        image = "https://bit.ly/2TIt8NR"
                    )
                    getStreamChatTokenUseCase().collect { token ->
                        withContext(Dispatchers.Main) {
                            streamChatUserMutable.value = Pair(user, token.toString())
                        }
                    }
                }
            }
        }
    }
}