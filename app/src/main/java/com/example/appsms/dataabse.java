package com.example.appsms;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.Atiran.Anbar.AccessDataBase.ReciveSmsDao;
import com.Atiran.Anbar.AccessDataBase.SendSmsDao;

import com.Atiran.Anbar.AccessDataBase.SendedSmsSmsDao;
import com.Atiran.Anbar.Tables.ReciveSms;

import com.Atiran.Anbar.Tables.SendedSms;
import com.example.appsms.Tables.SendSms;


@Database(entities = {ReciveSms.class, SendSms.class, SendedSms.class
},exportSchema = false,version = 3)
@TypeConverters({Converters.class})
abstract   public class dataabse extends RoomDatabase {
    static  String namedab="appsmsdatabase";
    static  dataabse instance;
    public static  synchronized  dataabse getInstances(Context context){
        if (instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    dataabse.class,
                    namedab)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    };



    public abstract SendSmsDao SendSmsDaoAccess();
    public abstract ReciveSmsDao ReciveSmsDaoAccess();
    public abstract SendedSmsSmsDao SendedSmsDaoAccess();

}
