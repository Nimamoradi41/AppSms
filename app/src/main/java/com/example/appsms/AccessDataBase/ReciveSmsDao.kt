package com.Atiran.Anbar.AccessDataBase

import androidx.room.*
import com.Atiran.Anbar.Tables.ReciveSms
import com.Atiran.Anbar.Tables.SendSms

@Dao
 public interface ReciveSmsDao {
    @Query("SELECT * from ReciveSms")
    fun GetReciveSms(): List<ReciveSms>


   @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Inser_SendSms(anb: ReciveSms)

    @Query("DELETE FROM ReciveSms")
    fun nukeTable();

    @Update
    fun updateReciveSms(anb:ReciveSms)

    @Delete()
    fun Delet_ReciveSms(anb: ReciveSms)
}