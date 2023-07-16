package com.kiranvarghese.MachineTest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kiranvarghese.MachineTest.data.models.Users
import com.kiranvarghese.MachineTest.databinding.ItemPassengerBinding

class PassengersAdapter (private val onClickListener: OnClickListener) :
    PagingDataAdapter<Users, PassengersAdapter.PassengersViewHolder>(PassengersComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengersViewHolder {
        return PassengersViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val meme = getItem(position)
        meme?.let { holder.bindPassenger(it) }

        holder.itemView.setOnClickListener {
            if (meme != null) {
                onClickListener.onClick(meme)
            }
        }
    }

    class OnClickListener(val clickListener: (meme: Users) -> Unit) {
        fun onClick(meme: Users) = clickListener(meme)
    }

    inner class PassengersViewHolder(private val binding: ItemPassengerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPassenger(item: Users) = with(binding) {
         item.image?.let { imageViewAirlinesLogo.loadImage(it) }
            textViewHeadquarters.text = item.firstName + "  " +item.lastName
//            textViewNameWithTrips.text = "Age "+ item.age + "\n" +
//                    "blood"+" " +item.bloodGroup
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id == newItem.id
        }

    }

}