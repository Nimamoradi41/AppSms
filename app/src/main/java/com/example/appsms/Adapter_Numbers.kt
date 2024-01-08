package com.example.appsms

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Atiran.Anbar.Model.*
import com.Atiran.Anbar.R
import kotlinx.android.synthetic.main.custom_fro.view.*
import kotlinx.android.synthetic.main.custom_fro.view.backk_color
import kotlinx.android.synthetic.main.custom_fro.view.checkBox3
import kotlinx.android.synthetic.main.custom_fro.view.textView39
import kotlinx.android.synthetic.main.custom_fro.view.textView41
import kotlinx.android.synthetic.main.custom_fro.view.textView43
import kotlinx.android.synthetic.main.custom_fro.view.textView67
import kotlinx.android.synthetic.main.custom_fro.view.view6
import kotlinx.android.synthetic.main.custom_tolid_items_barghashtkharid.view.*

class Adapter_Numbers(var C:Context) : RecyclerView.Adapter<Adapter_Numbers.view>() {

    var Flag=true
    var list:ArrayList<ListProduct>?=null
    var holder=""
    init {

        list=ArrayList<ListProduct>()
    }
    class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(C).inflate(R.layout.custom_tolid_items_barghashtkharid,parent,false)

        return view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {

        var Item=list?.get(position)

        if (Item?.Active!!)
        {
            holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#335A9318"))
            holder.itemView.checkBox3.isChecked=true
        }else{
            holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#FFFFFF"))
            holder.itemView.checkBox3.isChecked=false
        }


        if (Flag)
        {
            holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#EAEAEA"))
            Flag=false
            Item?.holder="#EAEAEA"
        }else{
            holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#FFFFFF"))
            Flag=true
            Item?.holder="#FFFFFF"
        }




//        if (Item?.Active!!)
//        {
//            holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#335A9318"))
//        }






        holder.itemView.setOnClickListener {
            holder.itemView.checkBox3.isChecked=!holder.itemView.checkBox3.isChecked
            if (Item?.Active!!)
            {
                holder.itemView.backk_color.setBackgroundColor(Color.parseColor(Item.holder))
                Item.Active=  holder.itemView.checkBox3.isChecked
            }else{
                holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#335A9318"))
                Item.Active=  holder.itemView.checkBox3.isChecked
            }
        }

        holder.itemView.checkBox3.setOnClickListener {
            if (Item?.Active!!)
            {
                holder.itemView.backk_color.setBackgroundColor(Color.parseColor(Item.holder))
                Item.Active=  holder.itemView.checkBox3.isChecked
            }else{
                holder.itemView.backk_color.setBackgroundColor(Color.parseColor("#335A9318"))
                Item.Active=  holder.itemView.checkBox3.isChecked
            }
        }


        if (!Item?.naka.isNullOrBlank())
        {
            holder.itemView.textView39.setText(Item?.naka)
        }else{
            holder.itemView.textView39.setText("نامشخص")
        }



        if (!Item?.tedad_forshow.toString().isNullOrBlank())
        {
            holder.itemView.textView41.setText(Item?.tedad_forshow.toString())
        }



//        if (!Item?.tedad_forshow.toString().isNullOrBlank())
//        {
//            var S=Item?.tedad_forshow.toString().split(".")
//            if (S.get(1).equals("0"))
//            {
//                holder.itemView.textView41.setText(S.get(0).toString())
//            }else{
//                holder.itemView.textView41.setText(Item?.tedad_forshow.toString())
//            }
//        }else{
//            holder.itemView.textView41.setText("نامشخص")
//        }




        if (!Item?.joz.toString().isNullOrBlank())
        {
            holder.itemView.textView43.setText(Item?.joz.toString())
        }else{
            holder.itemView.textView43.setText("نامشخص")
        }




        if (!Item?.productionSeries.toString().isNullOrBlank())
        {
            holder.itemView.textView207.setText(Item?.productionSeries.toString())
        }else{
            holder.itemView.textView207.setText("نامشخص")
        }





        if (!Item.Anbarname.toString().isNullOrBlank())
        {
            holder.itemView.textView67.setText(Item?.Anbarname.toString())
        }else{
            holder.itemView.textView67.setText("نامشخص")
        }


        if (position+1==list?.size)
        {
            holder.itemView.view6.visibility=View.GONE
        }






    }

    override fun getItemCount(): Int {
     return list?.size!!
    }
}