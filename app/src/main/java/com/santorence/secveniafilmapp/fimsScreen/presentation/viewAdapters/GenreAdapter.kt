package com.santorence.secveniafilmapp.fimsScreen.presentation.viewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.santorence.secveniafilmapp.databinding.GenreItemBinding
import com.santorence.secveniafilmapp.fimsScreen.domain.model.GenreModel
import com.santorence.secveniafilmapp.fimsScreen.presentation.viewHolders.GenreViewHolder
import com.santorence.secveniafilmapp.utils.diffUtils.GenreDiffUtilsCallback

class GenreAdapter(private val genreList: MutableList<GenreModel>) :
    RecyclerView.Adapter<GenreViewHolder>() {
    var onGenreClick: ((GenreModel) -> Unit) = { genre -> }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemBinding =
            GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(itemBinding, onGenreClick, parent.context)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genreItem = genreList[position]
        holder.bind(genreItem)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    fun setGenreList(updatedList: MutableList<GenreModel>) {
        val diffResult = DiffUtil.calculateDiff(GenreDiffUtilsCallback(genreList, updatedList))
        genreList.clear()
        genreList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }
}