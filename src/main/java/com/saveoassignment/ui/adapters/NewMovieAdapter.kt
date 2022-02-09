package com.saveoassignment.ui.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saveoassignment.R
import com.saveoassignment.databinding.ItemMovieListBinding
import com.saveoassignment.databinding.ItemMoviePagingBinding
import com.saveoassignment.infra.api.OnCardClicked
import com.saveoassignment.model.MovieModel
import com.saveoassignment.utils.Constant.MOVIE_URL
import java.util.*

class NewMovieAdapter(
    val resultModelList: List<MovieModel>,
    val onCardClicked: OnCardClicked
) : RecyclerView.Adapter<NewMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMoviesViewHolder {

        val binding: ItemMoviePagingBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_paging, parent, false
            )
        return NewMoviesViewHolder(binding, onCardClicked)
    }

    override fun onBindViewHolder(holder: NewMoviesViewHolder, position: Int) {
        val resultModel = resultModelList[position]
        holder.setData(resultModel)
    }

    override fun getItemCount(): Int {
        return resultModelList.size
    }


}

class NewMoviesViewHolder(
    val itemLayoutBinding: ItemMoviePagingBinding,
    val onCardClicked: OnCardClicked
) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun setData(resultModel: MovieModel) {
        itemLayoutBinding.apply {

            Glide.with(ivMovieCard).load(MOVIE_URL + resultModel.posterPath).into(ivMovieCard)

            ivMovieCard.setOnClickListener {
                onCardClicked.onCardClicked(resultModel)
            }
        }
    }
}


