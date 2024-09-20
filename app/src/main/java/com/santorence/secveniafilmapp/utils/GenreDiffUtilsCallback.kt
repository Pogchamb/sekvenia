package com.santorence.secveniafilmapp.utils

import androidx.recyclerview.widget.DiffUtil

class GenreDiffUtilsCallback (private val oldList: List<GenreItem>, private val newList: List<GenreItem>):
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].genre == newList[newItemPosition].genre
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].isChecked == newList[newItemPosition].isChecked && oldList[oldItemPosition].genre == newList[newItemPosition].genre -> true
            else -> false
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}