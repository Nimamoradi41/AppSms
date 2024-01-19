package com.example.appsms

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.Atiran.Anbar.Tables.ReciveSms
import com.example.appsms.Tables.SendSms

class Dial_App(var Type: Int, var S: String, var I: Interface_new, context: Context) : Dialog(context) {

    var dialog_holder: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    public interface Interface_new{
        public fun NewsSendSms(Type:String, Add : SendSms, Edit : SendSms)
        public fun NewsReciveSms(Type:String,num:ReciveSms,Edit : ReciveSms)
    }



}