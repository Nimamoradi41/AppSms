package com.example.appsms

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Atiran.Anbar.Tables.ReciveSms

import com.Atiran.Anbar.Tables.SendSms


class Adapter_NumbersRecive(var C:Context) : RecyclerView.Adapter<Adapter_NumbersRecive.view>() {

    var Flag=true
    var list:ArrayList<ReciveSms>?=null
    var holder=""
    init {

        list=ArrayList<ReciveSms>()
    }
    class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(C).inflate(R.layout.custom_tolid_items_barghashtkharid,parent,false)

        return view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {

//        var Item=list?.get(position)






//        if (Item?.Active!!)
//        {
//            holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#335A9318"))
//        }















    }

    override fun getItemCount(): Int {
//     return list?.size!!
     return 10
    }
}