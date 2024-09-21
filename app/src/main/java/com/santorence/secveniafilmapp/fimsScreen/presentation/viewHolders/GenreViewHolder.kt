package com.santorence.secveniafilmapp.fimsScreen.presentation.viewHolders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.santorence.secveniafilmapp.R
import com.santorence.secveniafilmapp.databinding.GenreItemBinding
import com.santorence.secveniafilmapp.fimsScreen.domain.model.GenreModel

class GenreViewHolder(
    private val genreItemBinding: GenreItemBinding,
    private val onGenreClick: (genre: GenreModel) -> Unit,
    private val context: Context
) : RecyclerView.ViewHolder(genreItemBinding.root) {


    fun bind(genreModel: GenreModel) {
        if (!genreModel.isChecked) {
            genreItemBinding.genreCardView.background = context.getDrawable(R.color.white)
        } else {
            genreItemBinding.genreCardView.background = context.getDrawable(R.color.orange)
        }
        genreItemBinding.genreText.text = genreModel.genre
        genreItemBinding.genreCardView.setOnClickListener {
            onGenreClick.invoke(genreModel)
        }
    }
}