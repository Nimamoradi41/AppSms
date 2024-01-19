package com.Atiran.Anbar.AccessDataBase


import androidx.room.*
import com.example.appsms.Tables.SendSms

@Dao
 public interface SendSmsDao {
    @Query("SELECT * from SendSms")
    fun GetSendSms(): List<SendSms>


    @Query("SELECT * from SendSms where iddatabase =:id")
    fun GetSendSmsById(id:Int): List<SendSms>





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSendSms(sendSmsList: SendSms): Long



    @Query("DELETE FROM SendSms")
    fun nukeTable();



   @Query("UPDATE SendSms SET Number = :Number , Name = :Name, idNumberRecive =:aa  WHERE iddatabase = :id2")
   fun updateSendSms(Number:String,Name:String,aa:List<Int>,id2:Int) :Int


    @Delete()
    fun Delet_SendSms(anb: SendSms)
}