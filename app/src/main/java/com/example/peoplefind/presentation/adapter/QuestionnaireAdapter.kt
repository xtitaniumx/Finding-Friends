package com.example.peoplefind.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ItemQuestionnaireBinding
import com.example.peoplefind.domain.model.response.QuestionnaireList

class QuestionnaireAdapter(
    private val listener: OnClickListener
) : ListAdapter<QuestionnaireList, QuestionnaireAdapter.Holder>(Comparator()) {

    interface OnClickListener {
        fun onCardClick(item: QuestionnaireList)
    }

    class Holder(itemView: View, listener: OnClickListener) : ViewHolder(itemView) {
        private val binding = ItemQuestionnaireBinding.bind(itemView)
        private lateinit var user: QuestionnaireList

        init {
            binding.imageProfileCard.setOnClickListener {
                listener.onCardClick(user)
            }
        }

        fun bind(item: QuestionnaireList) = with(binding) {
            user = item
            textProfileInfo.text = item.name
            textLocation.text = item.address.city
        }
    }

    class Comparator : DiffUtil.ItemCallback<QuestionnaireList>() {
        override fun areItemsTheSame(oldItem: QuestionnaireList, newItem: QuestionnaireList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuestionnaireList, newItem: QuestionnaireList): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_questionnaire, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}