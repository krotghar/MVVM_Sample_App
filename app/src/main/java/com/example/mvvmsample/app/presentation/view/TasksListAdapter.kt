package com.example.mvvmsample.app.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsample.databinding.ItemTaskBinding
import com.example.mvvmsample.domain.model.ModelTask

class TasksListAdapter(val itemClickListener: OnItemClickListener) : ListAdapter<ModelTask, ItemTaskViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ModelTask>() {
            override fun areItemsTheSame(oldItem: ModelTask, newItem: ModelTask) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: ModelTask, newItem: ModelTask) =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ItemTaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }
}

class ItemTaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ModelTask, itemClickListener: OnItemClickListener) {
        binding.tvTaskName.text = item.taskName
        binding.tvTaskDescription.text = item.taskDescription
        binding.root.setOnClickListener{
            itemClickListener.itemOnClicked(item)
        }
    }
}

interface OnItemClickListener {
    fun itemOnClicked(item: ModelTask)
}