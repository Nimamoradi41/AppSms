package com.example.appsms

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class Frag_Send : Fragment() {

    var adapter : Adapter_NumbersSend ?=null
    var RecyclerviewSend : RecyclerView ?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var MainView=  inflater.inflate(R.layout.fragment_frag__send, container, false)
        RecyclerviewSend=MainView.findViewById(R.id.RecyclerviewSend);


        adapter= Adapter_NumbersSend(requireContext())
        RecyclerviewSend?.adapter=adapter



        return  MainView
    }


}