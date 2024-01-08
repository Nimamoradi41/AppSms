package com.example.appsms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class Frag_Send : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var MainView=  inflater.inflate(R.layout.fragment_frag__send, container, false)




        return  MainView
    }


}