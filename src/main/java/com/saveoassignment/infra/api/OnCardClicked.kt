package com.saveoassignment.infra.api

import com.saveoassignment.model.MovieModel


interface OnCardClicked {

    fun onCardClicked(resultModel: MovieModel)

}