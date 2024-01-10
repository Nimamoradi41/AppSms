package com.Atiran.Anbar.Tables

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SendSms")
 class SendSms {
    @PrimaryKey(autoGenerate = true)
    var iddatabase:Int ?=null


    @ColumnInfo(name="Number")
    var Number :String ?=null



}