package com.example.appsms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.Atiran.Anbar.Tables.SendedSms
import com.example.appsms.Tables.SendSms


class Adapter_NumbersSended(var C:Context) : RecyclerView.Adapter<Adapter_NumbersSended.view>() {

    var Flag=true
    var list:List<SendedSms>?=null
    var holder=""

    var edit:Edit ? =null
    init {

        list=ArrayList<SendedSms>()
    }

    fun  Click( d:Edit)
    {
        this.edit=d
    }
    class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(C).inflate(R.layout.custom_tolid_items_barghashtkharid__3,parent,false)

        return view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
        var Item=list?.get(position)


        var textView6= holder.itemView.findViewById<TextView>(R.id.textView6)
        var textView15= holder.itemView.findViewById<TextView>(R.id.textView15)
        var textView3= holder.itemView.findViewById<TextView>(R.id.textView3)
        var textView13= holder.itemView.findViewById<TextView>(R.id.textView13)





        if (!Item?.idNumberSend.toString().isNullOrEmpty())
        {
            textView6.setText(Item?.idNumberSend)
        }



        if (!Item?.idNumberRecive.toString().isNullOrEmpty())
        {
            textView3.setText(Item?.idNumberRecive)
        }

        if (!Item?.Date.toString().isNullOrEmpty())
        {
            textView13.setText(Item?.Date)
        }


        if (!Item?.Time.toString().isNullOrEmpty())
        {
            textView15.setText(Item?.Time)
        }













    }



    interface  Edit {
        fun  EditItem(edit: SendSms)
        fun  RemoveItem(edit:SendSms)
    }
    override fun getItemCount(): Int {
     return list?.size!!
//     return 10
    }
}