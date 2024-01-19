package com.example.appsms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.Atiran.Anbar.Tables.SendedSms
import com.example.appsms.Tables.SendSms


class  ModelA{
    var id :Int? = null
    var name :String? = null
}
class Adapter_itemRecvi(var C:Context) : RecyclerView.Adapter<Adapter_itemRecvi.view>() {

    var Flag=true
    var list:ArrayList<ModelA>?=null
    var holder=""

    var edit:Edit ? =null
    init {
        list=ArrayList<ModelA>()
    }

    fun  Click( d:Edit)
    {
        this.edit=d
    }
    class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(C).inflate(R.layout.custom_tolid_items_barghashtkharid___4,parent,false)

        return view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
        var Item=list?.get(position)



        var textView3= holder.itemView.findViewById<TextView>(R.id.textView3)
        var textView2= holder.itemView.findViewById<ImageView>(R.id.textView2)









        if (!Item?.name.isNullOrEmpty())
        {
            textView3.setText(Item?.name)
        }




        textView2.setOnClickListener {
            edit?.RemoveItem(Item!!)
        }













    }



    interface  Edit {
        fun  EditItem(edit: SendSms)
        fun  RemoveItem(edit:ModelA)
    }
    override fun getItemCount(): Int {
     return list?.size!!
//     return 10
    }
}