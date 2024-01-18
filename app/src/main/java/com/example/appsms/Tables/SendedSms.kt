package com.Atiran.Anbar.Tables

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SendedSms")
 class SendedSms {
    @PrimaryKey(autoGenerate = true)
    var iddatabase:Int ?=null




   @ColumnInfo(name="idNumberRecive")
   var idNumberRecive:String ?=null

    @ColumnInfo(name="idNumberSend")
    var idNumberSend:String ?=null






    @ColumnInfo(name="Date")
    var Date :String ?=null


    @ColumnInfo(name="Time")
    var Time :String ?=null



}