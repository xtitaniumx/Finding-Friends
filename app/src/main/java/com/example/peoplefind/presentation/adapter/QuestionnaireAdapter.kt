package com.example.peoplefind.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ItemCardUserBinding
import com.example.peoplefind.domain.model.response.Questionnaire

class QuestionnaireAdapter(
    private val listener: OnClickListener
) : ListAdapter<Questionnaire, QuestionnaireAdapter.Holder>(Comparator()) {

    interface OnClickListener {
        fun onCardClick(item: Questionnaire)
    }

    class Holder(itemView: View, listener: OnClickListener) : ViewHolder(itemView) {
        private val binding = ItemCardUserBinding.bind(itemView)
        private lateinit var user: Questionnaire

        init {
            binding.imageProfileCard.setOnClickListener {
                listener.onCardClick(user)
            }
        }

        fun bind(item: Questionnaire) = with(binding) {
            user = item

            textProfileInfo.text = item.name
            textLocation.text = item.address.city
        }
    }

    class Comparator : DiffUtil.ItemCallback<Questionnaire>() {
        override fun areItemsTheSame(oldItem: Questionnaire, newItem: Questionnaire): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Questionnaire, newItem: Questionnaire): Boolean {
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