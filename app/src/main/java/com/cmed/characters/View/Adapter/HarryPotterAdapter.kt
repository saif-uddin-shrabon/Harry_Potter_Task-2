package com.cmed.characters.View.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.databinding.ListItemBinding

class HarryPotterAdapter(private val onHPClicked: (responseDataItem) -> Unit) : ListAdapter<responseDataItem, HarryPotterAdapter.HPViewHolder>(ComparatorDiffUtil()){
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
            binding.NmaeView.text = hp.name
            binding.ActorView.text = hp.actor
            binding.HouseView.text = hp.house
            Glide.with(itemView)
                .load(hp.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.profilePic)  // Replace 'binding.imageVie


            binding.root.setOnClickListener {
                onHPClicked(hp)
            }
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


