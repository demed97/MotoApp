package com.android.dan.motoapp.ui.moto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.dan.motoapp.R
import com.android.dan.motoapp.databinding.ItemMotoBinding
import com.android.dan.motoapp.entities.Moto
import com.android.dan.motoapp.utils.DiffUtilCallback

class MotoAdapter (motoList: List<Moto>) :
    RecyclerView.Adapter<MotoHolder>() {

    var motoList = motoList
        set(value) {
            val diffUtilCallback = DiffUtilCallback(value, field)
            val result = DiffUtil.calculateDiff(diffUtilCallback)
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotoHolder {
        return MotoHolder(
            DataBindingUtil.inflate<ItemMotoBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_moto,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MotoHolder, position: Int) {
        holder.itemMotoBinding.moto = motoList[position]
    }

    override fun getItemCount(): Int {
        return motoList.size
    }


}