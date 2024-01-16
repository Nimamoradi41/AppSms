
package com.example.appsms

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.Atiran.Anbar.Tables.ReciveSms
import com.Atiran.Anbar.Tables.SendSms
import com.google.android.material.internal.ViewUtils.showKeyboard
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class Frag_Recive : Fragment() {
    var adapter : Adapter_NumbersRecive ?=null
    var RecyclerviewSend : RecyclerView?=null
    var ReciveBtn : Button?=null



    var AddRecive : Button?=null
    var  database : dataabse?=null




    public fun Dialapp(S: String, S2:String, S3:String, I: Dial_App.Interface_new, context: Context, edit: ReciveSms): Dialog {
        var d = Dialog(context)

        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custome_dial_app, null, false)
        d.setContentView(view)
        d.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var textView4=view.findViewById<TextView>(R.id.textView4)
        var button2=view.findViewById<TextView>(R.id.button2)
        var button=view.findViewById<TextView>(R.id.button)

        textView4.setText(S3)
//            view.button8.setText(S)





//            view.button7.setText(S2)



        button2.setOnClickListener {
            d.dismiss()
            I.NewsReciveSms("0","",edit)
        }
        button.setOnClickListener {
            d.dismiss()
            I.NewsReciveSms("1","",edit)
        }
        return d
    }
    fun showKeyboard(V:View) {
        ViewCompat.getWindowInsetsController(V)?.show(WindowInsetsCompat.Type.ime())
    }
    public fun DialappAdd(S: String, S2:String, S3:String, I: Dial_App.Interface_new, context: Context, boolean: Boolean, S4: String, edit: ReciveSms): Dialog {
        var d = Dialog(context)

        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custome_dial_app_add, null, false)
        d.setContentView(view)
        d.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var Close=view.findViewById<ImageView>(R.id.imageView3)

        var button=view.findViewById<TextView>(R.id.button)
        var editTextPhone=view.findViewById<TextView>(R.id.editTextPhone)



        showKeyboard(editTextPhone)
        if (boolean)
        {
            editTextPhone.setText(S4)
        }

        Close.setOnClickListener {
            d.dismiss()
            I.NewsReciveSms("0","", ReciveSms())
        }
        button.setOnClickListener {
            if (editTextPhone.text.isEmpty())
            {
                Toast.makeText(requireContext(),"شماره را وارد کنید", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            d.dismiss()
            I.NewsReciveSms("1",editTextPhone.text.toString(),edit)
        }
        return d
    }




    suspend fun  Addnumber (number:String): Boolean {

        var AddOrNot=false
        var Sendn= ReciveSms()
        Sendn.Number=number

        var L=   database?.ReciveSmsDaoAccess()?.Inser_SendSms(Sendn)


        if (L!! >0)
        {
            AddOrNot=true
        }

        return  AddOrNot
    }

    suspend fun  Editnumber (number: ReciveSms): Boolean {





        var  Edited=true
        var L=   database?.ReciveSmsDaoAccess()?.updateReciveSms(
            number.Number.toString(),
            number.iddatabase!!
        )




//        if (L!! >0)
//        {
//            Edited=true
//        }

        return  Edited
    }

    suspend fun  DeleteNumberDataBase (number: ReciveSms): Boolean {


        var  Edited=true

        var L=   database?.ReciveSmsDaoAccess()?.Delet_ReciveSms(number,
        )



//        if (L!! >0)
//        {
//            Edited=true
//        }

        return  Edited
    }
    @OptIn(DelicateCoroutinesApi::class)
    public fun  AddNumberSend(){
        var D=DialappAdd("","","",object : Dial_App.Interface_new{
            override fun NewsSendSms(Type: String, num: String, Edit: SendSms) {

            }


            override fun NewsReciveSms(Type: String, num: String, Edit: ReciveSms) {
                if (Type.equals("1"))
                {
                    GlobalScope.launch{
                        Log.i("NIMa","A")
                        var  Res= async {
                            Addnumber(num)
                        }

                        var Rs=Res.await();
                        if (Rs)
                        {
                            GetAllSends();
                        }




                    }

                }
            }
        }, requireContext(),false,"", ReciveSms()).show()
    }



    fun  EditNumber(number: ReciveSms){
        var D=DialappAdd("","","",object : Dial_App.Interface_new{


            override fun NewsSendSms(Type: String, num: String, Edit: SendSms) {

            }

            override fun NewsReciveSms(Type: String, num: String, Edit: ReciveSms) {
                if (Type.equals("1"))
                {
                    Edit.Number=num
                    GlobalScope.launch{
                        var  Res= async {
                            Editnumber(Edit)
                        }

                        var Rs=Res.await();
                        if (Rs)
                        {
                            GetAllSends()
                        }


                    }

                }
            }
        }, requireContext(),true, number.Number.toString(),number).show()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun  GetAllSends(){

        runBlocking {
            GlobalScope.launch{
                var  Res= async {
                    GetAllSendNumbers()
                }


                var Rs=Res.await();
                withContext(Dispatchers.Main) {
                    adapter?.list=Rs
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }
    suspend fun GetAllSendNumbers() : List<ReciveSms> {
        return    database?.ReciveSmsDaoAccess()?.GetReciveSms()!!;
    }


    suspend fun  DeleteNumberDatBase (number:ReciveSms): Boolean {


        var  Edited=true

        var L=   database?.ReciveSmsDaoAccess()?.Delet_ReciveSms(number,
        )



//        if (L!! >0)
//        {
//            Edited=true
//        }

        return  Edited
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var MainView=  inflater.inflate(R.layout.fragment_frag__recive, container, false)
        RecyclerviewSend=MainView.findViewById(R.id.RecyclerviewRecive);
        ReciveBtn=MainView.findViewById(R.id.ReciveBtn);


        adapter= Adapter_NumbersRecive(requireContext())
        RecyclerviewSend?.adapter=adapter

        database= dataabse.getInstances(activity)

        GetAllSends();

        ReciveBtn?.setOnClickListener {
            AddNumberSend()
        }
        adapter= Adapter_NumbersRecive(requireContext())
        adapter?.Click(object :Adapter_NumbersRecive.Edit{
            override fun EditItem(edit: ReciveSms) {
                EditNumber(edit)
            }
            override fun RemoveItem(edit: ReciveSms) {

                var D=Dialapp("","","آیا مطمئن هستید؟",object : Dial_App.Interface_new{


                    override fun NewsSendSms(Type: String, num: String, Edit: SendSms) {

                    }

                    override fun NewsReciveSms(Type: String, num: String, Edit: ReciveSms) {
                        if (Type.equals("1"))
                        {

                            GlobalScope.launch{
                                var  Res= async {
                                    DeleteNumberDatBase(Edit)
                                }

                                var Rs=Res.await();
                                if (Rs)
                                {
                                    GetAllSends()
                                }


                            }

                        }
                    }
                }, requireContext(),edit).show()
            }

        })
        RecyclerviewSend?.adapter=adapter


        return  MainView
    }


}