package com.usaha.umkm.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usaha.umkm.data.model.ModelResponseLike
import com.usaha.umkm.databinding.ItemCommentBinding

class AdapterComment(val dataSet: ModelResponseLike):
    RecyclerView.Adapter<AdapterComment.ViewHolderComment>(){

    inner class ViewHolderComment(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderComment {
        val binding = ItemCommentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderComment(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderComment, position: Int) {
        with(holder){
            binding.apply {
                textDescription.text = dataSet.data?.get(position)?.komentar ?: ""
            }
        }
    }

    override fun getItemCount(): Int = dataSet.data?.size ?: 0
}