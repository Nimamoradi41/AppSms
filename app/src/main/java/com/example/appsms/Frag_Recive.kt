
package com.example.appsms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Frag_Recive.newInstance] factory method to
 * create an instance of this fragment.
 */
class Frag_Recive : Fragment() {
    var adapter : Adapter_NumbersRecive ?=null
    var RecyclerviewSend : RecyclerView?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var MainView=  inflater.inflate(R.layout.fragment_frag__recive, container, false)
        RecyclerviewSend=MainView.findViewById(R.id.RecyclerviewRecive);


        adapter= Adapter_NumbersRecive(requireContext())
        RecyclerviewSend?.adapter=adapter



        return  MainView
    }


}