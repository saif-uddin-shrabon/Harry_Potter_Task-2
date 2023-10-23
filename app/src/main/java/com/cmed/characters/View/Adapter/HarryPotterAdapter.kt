package com.cmed.characters.View.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.databinding.ListItemBinding

class HarryPotterAdapter() : ListAdapter<responseDataItem, HarryPotterAdapter.HPViewHolder>(ComparatorDiffUtil()){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HarryPotterAdapter.HPViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HPViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HPViewHolder, position: Int) {
        val hp = getItem(position)
        holder.bind(hp)

    }

    inner class HPViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hp: responseDataItem){
            binding.nametext.text = hp.name
            binding.actortext.text = hp.actor
            binding.housetext.text = hp.house
        }




    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<responseDataItem>() {
        override fun areItemsTheSame(oldItem: responseDataItem, newItem: responseDataItem): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: responseDataItem, newItem: responseDataItem): Boolean {
            return oldItem == newItem
        }
    }


}

