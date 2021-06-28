package com.anggasaraya.moviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.databinding.ItemsListBinding
import com.bumptech.glide.Glide

class TVShowAdapter : PagedListAdapter<TVShowEntity, TVShowAdapter.TVShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowAdapter.TVShowViewHolder {
        val view = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVShowAdapter.TVShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) holder.bind(tvShow)
    }

    inner class TVShowViewHolder(private val binding: ItemsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemDate.text = tvShow.dateReleased
                tvItemDescription.text = tvShow.description
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTVShowActivity::class.java)
                    intent.putExtra(DetailTVShowActivity.EXTRA_TVSHOW, tvShow.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${tvShow.imagePath}")
                    .into(imgPoster)
            }
        }
    }
}