package com.Atiran.Anbar.AccessDataBase

import androidx.room.*
import com.Atiran.Anbar.Tables.SendSms

@Dao
 public interface SendSmsDao {
    @Query("SELECT * from SendSms")
    fun GetSendSms(): List<SendSms>


   @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Inser_SendSms(anb: SendSms)

    @Query("DELETE FROM SendSms")
    fun nukeTable();

    @Update
    fun updateSendSms(anb:SendSms)

    @Delete()
    fun Delet_SendSms(anb: SendSms)
}