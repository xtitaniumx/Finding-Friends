package com.example.peoplefind.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ItemMessageBinding
import com.example.peoplefind.databinding.ItemMessageDateBinding
import com.example.peoplefind.databinding.ItemMessageMeetBinding
import com.example.peoplefind.databinding.ItemOwnMessageBinding
import com.example.peoplefind.databinding.ItemOwnMessageMeetBinding
import com.example.peoplefind.domain.model.response.ChatMessage
import com.example.peoplefind.domain.model.response.DateMessage
import com.example.peoplefind.domain.model.response.MeetMessage
import com.example.peoplefind.domain.model.response.Message

class ChatMessageAdapter(
    private val meetMessageListener: OnMeetClickListener
) : ListAdapter<ChatMessage, ChatMessageAdapter.Holder>(Comparator()) {

    companion object {
        private const val VIEW_TYPE_MESSAGE = 0
        private const val VIEW_TYPE_OWN_MESSAGE = 1
        private const val VIEW_TYPE_DATE_MESSAGE = 2
        private const val VIEW_TYPE_MEET_MESSAGE = 3
        private const val VIEW_TYPE_OWN_MEET_MESSAGE = 4
    }

    interface OnMeetClickListener {
        fun onMeetAcceptClick()

        fun onMeetRejectClick()
    }

    abstract class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: ChatMessage)
    }

    class MessageHolder(itemView: View) : Holder(itemView) {
        private val binding = ItemMessageBinding.bind(itemView)

        override fun bind(item: ChatMessage) = with(binding) {
            val messageItem = item as Message
            textMessage.text = messageItem.text
            textMessageTime.text = messageItem.time
        }
    }

    class OwnMessageHolder(itemView: View) : Holder(itemView) {
        private val binding = ItemOwnMessageBinding.bind(itemView)

        override fun bind(item: ChatMessage) = with(binding) {
            val ownMessageItem = item as Message
            textOwnMessage.text = ownMessageItem.text
            textOwnMessageTime.text = ownMessageItem.time
        }
    }

    class DateMessageHolder(itemView: View) : Holder(itemView) {
        private val binding = ItemMessageDateBinding.bind(itemView)

        override fun bind(item: ChatMessage) = with(binding) {
            val dateMessageItem = item as DateMessage
            textMessageDate.text = dateMessageItem.text
        }
    }

    class MeetMessageHolder(itemView: View, listener: OnMeetClickListener) : Holder(itemView) {
        private val binding = ItemMessageMeetBinding.bind(itemView)
        private lateinit var meetMessageItem: MeetMessage

        init {
            binding.buttonAccept.setOnClickListener {
                listener.onMeetAcceptClick()
            }
            binding.buttonReject.setOnClickListener {
                listener.onMeetRejectClick()
            }
        }

        override fun bind(item: ChatMessage) = with(binding) {
            meetMessageItem = item as MeetMessage
            textInviteUser.text = meetMessageItem.inviteUser
            textMeetDate.text = meetMessageItem.date
            textMeetPlace.text = meetMessageItem.place
            textMeetGoal.text = meetMessageItem.goal
            textMessageTime.text = meetMessageItem.time
        }
    }

    class OwnMeetMessageHolder(itemView: View) : Holder(itemView) {
        private val binding = ItemOwnMessageMeetBinding.bind(itemView)

        override fun bind(item: ChatMessage) = with(binding) {
            val ownMeetMessageItem = item as MeetMessage
            textMeetDate.text = ownMeetMessageItem.date
            textMeetPlace.text = ownMeetMessageItem.place
            textMeetGoal.text = ownMeetMessageItem.goal
            textMessageTime.text = ownMeetMessageItem.time
        }
    }

    class Comparator : DiffUtil.ItemCallback<ChatMessage>() {
        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (val item = getItem(position)) {
            is Message -> {
                // Это пример
                if (item.owner == 0) return VIEW_TYPE_MESSAGE
                if (item.owner == 1) return VIEW_TYPE_OWN_MESSAGE
            }
            is DateMessage -> return VIEW_TYPE_DATE_MESSAGE
            is MeetMessage -> {
                if (item.owner == 0) return VIEW_TYPE_MEET_MESSAGE
                if (item.owner == 1) return VIEW_TYPE_OWN_MEET_MESSAGE
            }
        }
        return VIEW_TYPE_MESSAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            VIEW_TYPE_MESSAGE -> {
                val view = inflater.inflate(R.layout.item_message, parent, false)
                return MessageHolder(view)
            }
            VIEW_TYPE_OWN_MESSAGE -> {
                val view = inflater.inflate(R.layout.item_own_message, parent, false)
                return OwnMessageHolder(view)
            }
            VIEW_TYPE_DATE_MESSAGE -> {
                val view = inflater.inflate(R.layout.item_message_date, parent, false)
                return DateMessageHolder(view)
            }
            VIEW_TYPE_MEET_MESSAGE -> {
                val view = inflater.inflate(R.layout.item_message_meet, parent, false)
                return MeetMessageHolder(view, meetMessageListener)
            }
            VIEW_TYPE_OWN_MEET_MESSAGE -> {
                val view = inflater.inflate(R.layout.item_own_message_meet, parent, false)
                return OwnMeetMessageHolder(view)
            }
        }
        val view = inflater.inflate(R.layout.item_own_message, parent, false)
        return OwnMessageHolder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}