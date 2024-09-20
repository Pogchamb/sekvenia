package com.santorence.secveniafilmapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.santorence.secveniafilmapp.fimsScreen.domain.model.FilmModel

class FilmsDiffUtilsCallback(private val oldList: List<FilmModel>, private val newList: List<FilmModel>):
DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].localizedName == newList[newItemPosition].localizedName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].localizedName == newList[newItemPosition].localizedName -> true
            else -> false
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}