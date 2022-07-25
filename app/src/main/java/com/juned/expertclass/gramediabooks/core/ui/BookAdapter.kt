package com.juned.expertclass.gramediabooks.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juned.expertclass.gramediabooks.R
import com.juned.expertclass.gramediabooks.core.domain.model.Book
import com.juned.expertclass.gramediabooks.databinding.ItemBookBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.ListViewHolder>() {

    private var listData = ArrayList<Book>()
    var onItemClick: ((Book) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Book>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBookBinding.bind(itemView)
        fun bind(data: Book) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivBookImage)
                tvBookTitle.text = data.title
                tvBookPrice.text = data.price
                tvBookAuthor.text = data.author
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}