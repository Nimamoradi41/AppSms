package com.Atiran.Anbar.AccessDataBase

import androidx.room.*
import com.Atiran.Anbar.Tables.ReciveSms

@Dao
 public interface ReciveSmsDao {
    @Query("SELECT * from ReciveSms")
    fun GetReciveSms(): List<ReciveSms>


   @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun Inser_SendSms(anb: ReciveSms): Long

    @Query("DELETE FROM ReciveSms")
    fun nukeTable();




    @Query("UPDATE ReciveSms SET Number = :Number, Name = :Name  WHERE iddatabase = :id2")
    fun updateReciveSms(Number:String,Name:String,id2:Int) :Int





    @Delete()
    fun Delet_ReciveSms(anb: ReciveSms)
}