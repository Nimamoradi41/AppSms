package com.example.appsms

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.Atiran.Anbar.Tables.ReciveSms
import com.Atiran.Anbar.Tables.SendSms
import kotlinx.coroutines.*


@Suppress("UNREACHABLE_CODE")
class Frag_SendedSms(var act:Activity) : Fragment() {

    var adapter : Adapter_NumbersSend ?=null
    var RecyclerviewSend : RecyclerView ?=null
    var AddSend : Button ?=null
    var  database : dataabse?=null





























    suspend fun GetAllSendNumbers() : List<SendSms> {
      return    database?.SendSmsDaoAccess()?.GetSendSms()!!;
    }
    fun showKeyboard(V:View) {
        ViewCompat.getWindowInsetsController(V)?.show(WindowInsetsCompat.Type.ime())
    }


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override   fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var MainView=  inflater.inflate(R.layout.fragment_frag__sendedsms, container, false)

        database= dataabse.getInstances(act)




        RecyclerviewSend=MainView.findViewById(R.id.RecyclerviewSend);
        AddSend=MainView.findViewById(R.id.AddSend);


        AddSend?.setOnClickListener {

        }





        return  MainView
    }


}