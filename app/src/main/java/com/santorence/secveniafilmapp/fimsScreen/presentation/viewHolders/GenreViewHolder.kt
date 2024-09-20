package com.santorence.secveniafilmapp.fimsScreen.presentation.viewHolders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.santorence.secveniafilmapp.R
import com.santorence.secveniafilmapp.databinding.GenreItemBinding
import com.santorence.secveniafilmapp.utils.GenreItem

class GenreViewHolder(
    private val genreItemBinding: GenreItemBinding,
    private val onGenreClick: (genre: GenreItem) -> Unit,
    private val context: Context
): RecyclerView.ViewHolder(genreItemBinding.root) {


    fun bind(genreItem: GenreItem) {
        if (!genreItem.isChecked) {
            genreItemBinding.genreCardView.background = context.getDrawable(R.color.white)
        } else {
            genreItemBinding.genreCardView.background = context.getDrawable(R.color.orange)
        }
        genreItemBinding.genreText.text = genreItem.genre
        genreItemBinding.genreCardView.setOnClickListener {
            onGenreClick.invoke(genreItem)
        }
    }
}