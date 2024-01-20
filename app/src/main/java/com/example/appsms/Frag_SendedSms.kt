package com.example.appsms

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.Atiran.Anbar.Tables.SendedSms
import kotlinx.coroutines.*
import saman.zamani.persiandate.PersianDate


@Suppress("UNREACHABLE_CODE")
class Frag_SendedSms(var act:Activity) : Fragment() {

    var adapter : Adapter_NumbersSended ?=null
    var RecyclerviewSend : RecyclerView ?=null
    var textView11 : TextView ?=null
    var textView10 : TextView ?=null
    var imageView4 : ImageView ?=null
    var AddSend : Button ?=null
    var  database : dataabse?=null






    @SuppressLint("NotifyDataSetChanged")
    fun  GetAllSends(){
        runBlocking {
            GlobalScope.launch{
                var  Res= async {
                    GetAllSendNumbers()
                }
                var Rs=Res.await();
                withContext(Dispatchers.Main) {
                    var Temp=ArrayList<SendedSms>()
                    Rs.forEach {
                        if (it.Date==currentDate)
                        {
                            Temp.add(it)
                        }
                    }
                    adapter?.list=Temp
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }


    fun Convert_DATE2(day: String, month: String, year: String): String? {
        var temp_day = ""
        var temp_mont = ""
        temp_day = if (day.length == 1) {
            "0$day"
        } else {
            day
        }
        temp_mont = if (month.length == 1) {
            "0$month"
        } else {
            month
        }
        return "$year/$temp_mont/$temp_day"
    }
    suspend fun GetAllSendNumbers() : List<SendedSms> {
      return    database?.SendedSmsDaoAccess()?.GetSendSms()!!;
    }
    fun showKeyboard(V:View) {
        ViewCompat.getWindowInsetsController(V)?.show(WindowInsetsCompat.Type.ime())
    }

    var currentDate=""
    var Temp_Day_1=0
    var Temp_Month_1=0
    var Temp_Year_1=0
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override   fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var MainView=  inflater.inflate(R.layout.fragment_frag__sendedsms, container, false)

        database= dataabse.getInstances(act)
        textView11=MainView.findViewById(R.id.textView11);
        textView10=MainView.findViewById(R.id.textView10);
        imageView4=MainView.findViewById(R.id.imageView4);

        val persianDate = PersianDate()
          currentDate=
              Convert_DATE2(persianDate.shDay.toString(), persianDate.shMonth.toString(), persianDate.shYear.toString()).toString()
        textView11?.setText(currentDate)
        try {
            Temp_Day_1= persianDate?.shDay!!
            Temp_Month_1= persianDate?.shMonth!!-1
            Temp_Year_1= persianDate?.shYear!!
        }catch (Ex:java.lang.Exception)
        {
            Temp_Day_1= persianDate?.shDay!!
            Temp_Month_1= persianDate?.shMonth!!
            Temp_Year_1= persianDate?.shYear!!
        }
        GetAllSends();
        RecyclerviewSend=MainView.findViewById(R.id.RecyclerviewSend);

        AddSend=MainView.findViewById(R.id.AddSend);


        adapter= Adapter_NumbersSended(context!!)
        RecyclerviewSend?.adapter=adapter

        AddSend?.setOnClickListener {
            GetAllSends();
        }

        textView11?.setOnClickListener {

            Log.i("Dvsdvf", Temp_Day_1.toString())
            Log.i("Dvsdvf", Temp_Month_1.toString())
            Log.i("Dvsdvf", Temp_Year_1.toString())

            val datePickerDialog: com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog? = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
                object : com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(
                        view: com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog?,
                        year: Int,
                        monthOfYear: Int,
                        dayOfMonth: Int
                    ) {
                        textView11?.setText(Convert_DATE2(dayOfMonth.toString(),(monthOfYear+1).toString(),year.toString()))

                        currentDate=Convert_DATE2(dayOfMonth.toString(),(monthOfYear+1).toString(),year.toString()).toString()
                        Temp_Year_1=year
                        Temp_Month_1=monthOfYear
                        Temp_Day_1=dayOfMonth

                    }
                },
                Temp_Year_1,
                Temp_Month_1,
                Temp_Day_1,

            )
            datePickerDialog?.show(act.fragmentManager, "Datepickerdialog")

        }
        textView10?.setOnClickListener {

            Log.i("Dvsdvf", Temp_Day_1.toString())
            Log.i("Dvsdvf", Temp_Month_1.toString())
            Log.i("Dvsdvf", Temp_Year_1.toString())

            val datePickerDialog: com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog? = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
                object : com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(
                        view: com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog?,
                        year: Int,
                        monthOfYear: Int,
                        dayOfMonth: Int
                    ) {
                        textView11?.setText(Convert_DATE2(dayOfMonth.toString(),(monthOfYear+1).toString(),year.toString()))

                        currentDate=Convert_DATE2(dayOfMonth.toString(),(monthOfYear+1).toString(),year.toString()).toString()
                        Temp_Year_1=year
                        Temp_Month_1=monthOfYear
                        Temp_Day_1=dayOfMonth

                    }
                },
                Temp_Year_1,
                Temp_Month_1,
                Temp_Day_1,

                )
            datePickerDialog?.show(act.fragmentManager, "Datepickerdialog")

        }
        imageView4?.setOnClickListener {

            Log.i("Dvsdvf", Temp_Day_1.toString())
            Log.i("Dvsdvf", Temp_Month_1.toString())
            Log.i("Dvsdvf", Temp_Year_1.toString())

            val datePickerDialog: com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog? = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
                object : com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(
                        view: com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog?,
                        year: Int,
                        monthOfYear: Int,
                        dayOfMonth: Int
                    ) {
                        textView11?.setText(Convert_DATE2(dayOfMonth.toString(),(monthOfYear+1).toString(),year.toString()))

                        currentDate=Convert_DATE2(dayOfMonth.toString(),(monthOfYear+1).toString(),year.toString()).toString()
                        Temp_Year_1=year
                        Temp_Month_1=monthOfYear
                        Temp_Day_1=dayOfMonth

                    }
                },
                Temp_Year_1,
                Temp_Month_1,
                Temp_Day_1,

                )
            datePickerDialog?.show(act.fragmentManager, "Datepickerdialog")

        }








        return  MainView
    }


}