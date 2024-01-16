package com.example.appsms

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.*

@Suppress("UNREACHABLE_CODE")
class Frag_Send(var act:Activity) : Fragment() {

    var adapter : Adapter_NumbersSend ?=null
    var RecyclerviewSend : RecyclerView ?=null
    var AddSend : Button ?=null
    var  database : dataabse?=null


    public fun Dialapp(S: String,S2:String,S3:String, I: Dial_App.Interface_new, context: Context,edit:SendSms): Dialog {
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
            I.NewsSendSms("0","",edit)
        }
        button.setOnClickListener {
            d.dismiss()
            I.NewsSendSms("1","",edit)
        }
        return d
    }
    public fun DialappAdd(S: String,S2:String,S3:String, I: Dial_App.Interface_new, context: Context,boolean: Boolean,S4: String,edit:SendSms): Dialog {
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

//            view.button8.setText(S)





//            view.button7.setText(S2)



        Close.setOnClickListener {
            d.dismiss()
            I.NewsSendSms("0","", SendSms())
        }
        button.setOnClickListener {
            if (editTextPhone.text.isEmpty())
            {
                Toast.makeText(requireContext(),"شماره را وارد کنید",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            d.dismiss()
            I.NewsSendSms("1",editTextPhone.text.toString(),edit)
        }
        return d
    }




    suspend fun  Addnumber (number:String): Boolean {

       var AddOrNot=false
        var Sendn= SendSms()
        Sendn.Number=number

       var L=   database?.SendSmsDaoAccess()?.insertSendSms(Sendn)


        if (L!! >0)
        {
            AddOrNot=true
        }

        return  AddOrNot
    }

    suspend fun  Editnumber (number:SendSms): Boolean {


        var  Edited=true

        var L=   database?.SendSmsDaoAccess()?.updateSendSms(number.Number.toString(),
            number.iddatabase!!
        )



//        if (L!! >0)
//        {
//            Edited=true
//        }

        return  Edited
    }



    suspend fun  DeleteNumberDatBase (number:SendSms): Boolean {


        var  Edited=true

        var L=   database?.SendSmsDaoAccess()?.Delet_SendSms(number,
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

            override fun NewsReciveSms(Type: String, num: String, Edit: ReciveSms) {

            }
        }, requireContext(),false,"",SendSms()).show()
    }



    fun  EditNumber(number:SendSms){
        var D=DialappAdd("","","",object : Dial_App.Interface_new{


            override fun NewsSendSms(Type: String, num: String, Edit: SendSms) {
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

            override fun NewsReciveSms(Type: String, num: String, Edit: ReciveSms) {

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
        var MainView=  inflater.inflate(R.layout.fragment_frag__send, container, false)

        database= dataabse.getInstances(act)

        GetAllSends();


        RecyclerviewSend=MainView.findViewById(R.id.RecyclerviewSend);
        AddSend=MainView.findViewById(R.id.AddSend);


        AddSend?.setOnClickListener {
            AddNumberSend()
        }



        adapter= Adapter_NumbersSend(requireContext())
        adapter?.Click(object :Adapter_NumbersSend.Edit{
            override fun EditItem(edit: SendSms) {
                EditNumber(edit)
            }
            override fun RemoveItem(edit: SendSms) {

                var D=Dialapp("","","آیا مطمئن هستید؟",object : Dial_App.Interface_new{


                    override fun NewsSendSms(Type: String, num: String, Edit: SendSms) {
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
                    override fun NewsReciveSms(Type: String, num: String, Edit: ReciveSms) {

                    }
                }, requireContext(),edit).show()
            }

        })

        RecyclerviewSend?.adapter=adapter



        return  MainView
    }


}