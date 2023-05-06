package com.example.peoplefind.domain.model.response

sealed interface ChatMessage

data class DateMessage(val text: String) : ChatMessage
data class MeetMessage(val date: String, val street: String, val description: String) : ChatMessage
data class Message(val owner: Int, val text: String, val time: String) : ChatMessage