package com.example.appsms

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.Atiran.Anbar.Tables.SendSms


class Adapter_NumbersSend(var C:Context) : RecyclerView.Adapter<Adapter_NumbersSend.view>() {

    var Flag=true
    var list:List<SendSms>?=null
    var holder=""

    var edit:Edit ? =null
    init {

        list=ArrayList<SendSms>()
    }

    fun  Click( d:Edit)
    {
        this.edit=d
    }
    class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(C).inflate(R.layout.custom_tolid_items_barghashtkharid_2,parent,false)

        return view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
        var Item=list?.get(position)


        var textView6= holder.itemView.findViewById<TextView>(R.id.textView6)
        var textView3= holder.itemView.findViewById<TextView>(R.id.textView3)
        var textView8= holder.itemView.findViewById<TextView>(R.id.textView8)
        var imageView2= holder.itemView.findViewById<ImageView>(R.id.imageView2)
        var imageView= holder.itemView.findViewById<ImageView>(R.id.imageView)




        if (!Item?.Name.toString().isNullOrEmpty())
        {
            textView3.setText(Item?.Name)
        }


        if (!Item?.Number.toString().isNullOrEmpty())
        {
            textView6.setText(Item?.Number)
        }

        if (!Item?.NameReciveNumber.toString().isNullOrEmpty())
        {
            textView8.setText(Item?.NameReciveNumber)
        }







        imageView2.setOnClickListener {
            edit?.EditItem(Item!!)
        }

        imageView.setOnClickListener {
            edit?.RemoveItem(Item!!)
        }














    }



    interface  Edit {
        fun  EditItem(edit:SendSms)
        fun  RemoveItem(edit:SendSms)
    }
    override fun getItemCount(): Int {
     return list?.size!!
//     return 10
    }
}