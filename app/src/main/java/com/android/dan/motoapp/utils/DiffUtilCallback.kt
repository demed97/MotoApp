package com.android.dan.motoapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.android.dan.motoapp.entities.Moto

class DiffUtilCallback (var newList: List<Moto>, var oldList: List<Moto> ) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}