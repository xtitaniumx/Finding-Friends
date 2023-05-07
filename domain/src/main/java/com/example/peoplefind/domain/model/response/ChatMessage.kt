package com.example.peoplefind.domain.model.response

sealed interface ChatMessage

data class DateMessage(val text: String) : ChatMessage
data class MeetMessage(val owner: Int, val inviteUser: String, val date: String, val place: String, val goal: String, val time: String) : ChatMessage
data class Message(val owner: Int, val text: String, val time: String) : ChatMessage