package com.santorence.secveniafilmapp.fimsScreen.presentation.viewHolders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santorence.secveniafilmapp.R
import com.santorence.secveniafilmapp.databinding.FilmItemBinding
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel

class FilmsViewHolder(
    private val context: Context,
    private val filmItemBinding: FilmItemBinding,
    private val onFilmClick: (film: FilmModel) -> Unit
) : RecyclerView.ViewHolder(filmItemBinding.root) {


    fun bind(filmModel: FilmModel) {
        filmItemBinding.localizedName.text = filmModel.localizedName

        filmItemBinding.root.setOnClickListener {
            onFilmClick.invoke(filmModel)
        }

        Glide.with(context).load(filmModel.imageUrl).placeholder(R.drawable.empty_image)
            .into(filmItemBinding.image)
    }


}