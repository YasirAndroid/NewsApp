package com.demo.newsdemo.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.newsdemo.R
import com.demo.newsdemo.model.Article
import com.google.android.material.textview.MaterialTextView

class NewsAdapter(var context: Context, var data: MutableList<Article>, private var onItemDelete: ((updatedList: Article) -> Unit), private var onItemClicked: ((url: String) -> Unit)) :
    RecyclerView.Adapter<NewsAdapter.dataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        return dataViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: dataViewHolder, position: Int) {
        val mainData = data[position]
        holder.title.text = mainData.title
        if (mainData.author==null) holder.author.text = "Author - N/A"
        else holder.author.text = "Author - ${mainData.author}"

        holder.root.setOnClickListener {
            onItemClicked(mainData.url)
        }
        Glide
            .with(context)
            .load(mainData.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.img_placeholder)
            .into(holder.image)

        holder.delete.setOnClickListener {
            removeAt(position)
            onItemDelete(mainData)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class dataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.tv_title) as MaterialTextView
        val author = itemView.findViewById(R.id.tv_author) as MaterialTextView
        val image = itemView.findViewById(R.id.iv_news_image) as ImageView
        val delete = itemView.findViewById(R.id.iv_delete) as ImageView
        val root = itemView.findViewById(R.id.root_item) as ConstraintLayout
    }

    fun removeAt(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, data.size)
    }
}