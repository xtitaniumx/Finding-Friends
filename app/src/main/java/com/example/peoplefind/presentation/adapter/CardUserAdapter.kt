package com.example.peoplefind.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ItemCardUserBinding
import com.example.peoplefind.domain.model.response.UserItem

class CardUserAdapter(
    private val listener: OnClickListener
) : ListAdapter<UserItem, CardUserAdapter.Holder>(Comparator()) {

    interface OnClickListener {
        fun onCardClick(item: UserItem)
    }

    class Holder(itemView: View, listener: OnClickListener) : ViewHolder(itemView) {
        private val binding = ItemCardUserBinding.bind(itemView)
        private lateinit var userItem: UserItem

        init {
            binding.imageProfileCard.setOnClickListener {
                listener.onCardClick(userItem)
            }
        }

        fun bind(item: UserItem) = with(binding) {
            userItem = item

            textProfileInfo.text = item.name
            textLocation.text = item.address.city
        }
    }

    class Comparator : DiffUtil.ItemCallback<UserItem>() {
        override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}