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
            I.NewsSendSms("0", SendSms(),edit)
        }
        button.setOnClickListener {
            d.dismiss()
            I.NewsSendSms("1", SendSms(),edit)
        }
        return d
    }








    suspend fun GetAllRecivNumbers() : List<ReciveSms> {
        return    database?.ReciveSmsDaoAccess()?.GetReciveSms()!!;
    }

    public fun DialappAdd(S: String,S2:String,S3:String, I: Dial_App.Interface_new, context: Context,boolean: Boolean,S4: String,edit:SendSms): Dialog {
        var d = Dialog(context)

        var Selected=-1
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custome_dial_app_add, null, false)
        d.setContentView(view)
        d.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var Close=view.findViewById<ImageView>(R.id.imageView3)
        var spinner=view.findViewById<Spinner>(R.id.spinner)

        var button=view.findViewById<TextView>(R.id.button)
        var editTextName=view.findViewById<TextView>(R.id.editTextPhone)
        var editTextPhone=view.findViewById<TextView>(R.id.editTextPhone2)





        var ListItems=ArrayList<String>()
        var MainList= ArrayList<ReciveSms>()

        runBlocking {
            GlobalScope.launch{
                var  Res= async {
                    GetAllRecivNumbers()
                }


                var Rs=Res.await();
                MainList= Rs as ArrayList<ReciveSms>
                withContext(Dispatchers.Main) {

                    Rs.forEach {
                        ListItems.add(it.Number+" "+it.Name)
                    }


                    val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item,ListItems)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



                    spinner.adapter = adapter
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>?,
                            selectedItemView: View,
                            position: Int,
                            id: Long
                        ) {

                            Selected=position
                        }

                        override fun onNothingSelected(parentView: AdapterView<*>?) {
                            if (boolean)
                            {

                            }else{
                                if (ListItems.isNotEmpty())
                                {
                                    Selected=0
                                }
                            }

                        }
                    }


                    if (boolean!!)
                    {
                        Selected= edit.idNumberRecive!!
                        spinner.setSelection(Selected)
                    }

                }
            }
        }










        showKeyboard(editTextPhone)
        if (boolean)
        {
            editTextPhone.setText(edit.Number)
            editTextName.setText(edit.Name)
        }

//            view.button8.setText(S)





//            view.button7.setText(S2)



        Close.setOnClickListener {
            d.dismiss()
            I.NewsSendSms("0", SendSms(), SendSms())
        }
        button.setOnClickListener {
            if (editTextPhone.text.isEmpty())
            {
                Toast.makeText(requireContext(),"شماره را وارد کنید",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (editTextName.text.isEmpty())
            {
                Toast.makeText(requireContext(),"نام شماره  را وارد کنید",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (!boolean)
            {
                if (Selected==-1)
                {
                    Toast.makeText(requireContext(),"سرشماره دریافتی را وارد کنید",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }



            var Temp=SendSms()
            Temp.Name=editTextName.text.toString()
            Temp.Number=editTextPhone.text.toString()
            Temp.idNumberRecive=MainList.get(Selected).iddatabase
            Temp.NameReciveNumber=(MainList.get(Selected).Name+""+MainList.get(Selected).Number.toString()).toString()


            d.dismiss()


            I.NewsSendSms("1",Temp,edit)
        }
        return d
    }





    suspend fun  Addnumber (number:SendSms): Boolean {

       var AddOrNot=false


       var L=   database?.SendSmsDaoAccess()?.insertSendSms(number)


        if (L!! >0)
        {
            AddOrNot=true
        }

        return  AddOrNot
    }

    suspend fun  Editnumber (number:SendSms): Boolean {


        var  Edited=true

        var L=   database?.SendSmsDaoAccess()?.updateSendSms(number.Number.toString(),
            number.Name.toString(),
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

        var L=   database?.SendSmsDaoAccess()?.Delet_SendSms(
            number,
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


            override fun NewsSendSms(Type: String, num: SendSms, Edit: SendSms) {
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

            override fun NewsReciveSms(Type: String, num: ReciveSms, Edit: ReciveSms) {

            }
        }, requireContext(),false,"",SendSms()).show()
    }


    fun  EditNumber(number:SendSms){
        var D=DialappAdd("","","",object : Dial_App.Interface_new{


            override fun NewsSendSms(Type: String, num: SendSms, Edit: SendSms) {
                if (Type.equals("1"))
                {
                    Edit.Number=num.Number
                    Edit.Name=num.Name

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

            override fun NewsReciveSms(Type: String, num: ReciveSms, Edit: ReciveSms) {

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


                    override fun NewsSendSms(Type: String, num: SendSms, Edit: SendSms) {
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
                    override fun NewsReciveSms(Type: String, num: ReciveSms, Edit: ReciveSms) {

                    }
                }, requireContext(),edit).show()
            }

        })
        RecyclerviewSend?.adapter=adapter
        return  MainView
    }


}