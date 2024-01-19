package com.Atiran.Anbar.AccessDataBase

import androidx.room.*
import com.Atiran.Anbar.Tables.SendedSms

@Dao
 public interface SendedSmsSmsDao {
    @Query("SELECT * from SendedSms")
    fun GetSendSms(): List<SendedSms>


    @Query("SELECT * from SendedSms where iddatabase =:id")
    fun GetSendedSmsById(id:Int): List<SendedSms>





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSendedSms(sendSmsList: SendedSms): Long



    @Query("DELETE FROM SendedSms")
    fun nukeTable();






    @Delete()
    fun Delet_SendSms(anb: SendedSms)
}