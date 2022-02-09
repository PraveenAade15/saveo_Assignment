package com.saveoassignment.ui.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saveoassignment.R
import com.saveoassignment.databinding.ItemMovieListBinding
import com.saveoassignment.databinding.ItemMoviePagingBinding
import com.saveoassignment.infra.api.OnCardClicked
import com.saveoassignment.model.MovieModel
import com.saveoassignment.utils.Constant
import java.util.*

class MovieAdapter(val onCardClicked: OnCardClicked) :
    PagingDataAdapter<MovieModel, MovieHolder>(diffUtil) {

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val resultModel = getItem(position)
        if (resultModel != null) {
            holder.setData(resultModel)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding: ItemMovieListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_list
                , parent, false
            )
        return MovieHolder(binding, onCardClicked)

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class MovieHolder(
    private val itemLayoutGirdBinding: ItemMovieListBinding,
    private val onCardClicked: OnCardClicked
) : RecyclerView.ViewHolder(itemLayoutGirdBinding.root) {

    fun setData(resultModel: MovieModel) {

        itemLayoutGirdBinding.apply {
            Glide.with(ivMovieCard).load(Constant.MOVIE_URL + resultModel.posterPath)
                .into(ivMovieCard)

            ivMovieCard.setOnClickListener {
                onCardClicked.onCardClicked(resultModel)
            }
        }
    }
}
