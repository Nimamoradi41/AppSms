package com.example.appsms

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.Atiran.Anbar.Tables.ReciveSms
import com.Atiran.Anbar.Tables.SendSms

class Dial_App(var Type: Int, var S: String, var I: Interface_new, context: Context) : Dialog(context) {

    var dialog_holder: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    public interface Interface_new{
        public fun NewsSendSms(Type:String,Add : SendSms,Edit : SendSms)
        public fun NewsReciveSms(Type:String,num:ReciveSms,Edit : ReciveSms)
    }



}