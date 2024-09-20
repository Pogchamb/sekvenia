package com.santorence.secveniafilmapp.fimsScreen.presentation.viewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.santorence.secveniafilmapp.databinding.FilmItemBinding
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel
import com.santorence.secveniafilmapp.fimsScreen.presentation.viewHolders.FilmsViewHolder
import com.santorence.secveniafilmapp.utils.FilmsDiffUtilsCallback

class FilmsAdapter(private val filmsModelList: MutableList<FilmModel>) :
    RecyclerView.Adapter<FilmsViewHolder>() {
    var onFilmClick:   ((FilmModel) -> Unit) = { filmModel ->  }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val itemBinding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(parent.context ,itemBinding, onFilmClick)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val filmItem = filmsModelList[position]
        holder.bind(filmItem)
    }

    override fun getItemCount(): Int {
        return filmsModelList.size
    }

    fun setFilmsList(updatedList: List<FilmModel>) {
        val diffResult = DiffUtil.calculateDiff(FilmsDiffUtilsCallback(filmsModelList, updatedList))
            filmsModelList.clear()
            filmsModelList.addAll(updatedList)
            diffResult.dispatchUpdatesTo(this)
    }
}