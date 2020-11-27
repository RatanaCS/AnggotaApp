package com.sumuzu.anggotaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumuzu.anggotaapp.R
import com.sumuzu.anggotaapp.model.getData.DataItem
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter (val data : List<DataItem>?, val itemClick : OnClickListener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder (val view : View) : RecyclerView.ViewHolder(view) {
        val nama = view.tvNama
        val noHP = view.tvNoHP
        val alamat = view.tvAlamat
        val delete = view.ivDelete
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item : DataItem? =data?.get(position)

        holder.nama.text = item?.nama
        holder.noHP.text = item?.nohp
        holder.alamat.text = item?.alamat

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

        holder.delete.setOnClickListener {
            itemClick.delete(item)
        }

    }

    override fun getItemCount(): Int = data?.size ?:0

    interface OnClickListener{
        fun detail(item: DataItem?)
        fun delete(item: DataItem?)

    }


}