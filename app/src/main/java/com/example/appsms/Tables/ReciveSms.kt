package com.Atiran.Anbar.Tables

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ReciveSms")
 class ReciveSms {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    var iddatabase:Int ?=null


    @ColumnInfo(name="Number")
    var Number :String ?=null

    @ColumnInfo(name="Name")
    var Name :String ?=null



}