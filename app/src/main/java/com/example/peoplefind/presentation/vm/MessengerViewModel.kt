package com.example.peoplefind.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peoplefind.domain.usecase.GetTokenUseCase
import com.example.peoplefind.domain.usecase.GetUserIdUseCase
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MessengerViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getStreamChatTokenUseCase: GetTokenUseCase.GetStreamChatToken
) : ViewModel() {
    private val userIdMutable = MutableLiveData<String>()
    val userId: LiveData<String> = userIdMutable

    private val streamChatUserMutable = MutableLiveData<Pair<User, String>>()
    val streamChatUser: LiveData<Pair<User, String>> = streamChatUserMutable

    private val channelMutable = MutableLiveData<Channel>()
    val channel: LiveData<Channel> = channelMutable

    fun getUserId() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserIdUseCase().collect { userId ->
                userId?.let {
                    userIdMutable.value = it
                }
            }
        }
    }

    fun getStreamChatUser(userId: String, userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(
                id = userId,
                name = userName,
                image = "https://bit.ly/2TIt8NR"
            )
            getStreamChatTokenUseCase().collect { token ->
                withContext(Dispatchers.Main) {
                    streamChatUserMutable.value = Pair(user, token.toString())
                }
            }
        }
    }

    fun beginChat(chatUserId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserIdUseCase().collect { userId ->
                userId?.let {
                    val client = ChatClient.instance()

                    client.createChannel(
                        channelType = "messaging",
                        channelId = "",
                        memberIds = listOf(it, chatUserId),
                        extraData = emptyMap()
                    ).enqueue { result ->
                        if (result.isSuccess) {
                            channelMutable.value = result.data()
                        } else {
                            Timber.d(result.error().message)
                        }
                    }
                }
            }
        }
    }
}