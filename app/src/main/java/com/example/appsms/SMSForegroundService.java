package com.example.appsms;

import static android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.util.Log;
import android.widget.Toast;

import com.Atiran.Anbar.Tables.ReciveSms;
import com.Atiran.Anbar.Tables.SendedSms;
import com.example.appsms.Tables.SendSms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import saman.zamani.persiandate.PersianDate;

public class SMSForegroundService extends Service {

    private static final String TAG = "SMSForegroundService";
    private static final String CHANNEL_ID = "SMSForegroundServiceChannel52222";
    private BroadcastReceiver smsReceiver;


    @Override
    public void onCreate() {
        super.onCreate();
        // ثبت رسیور برای دریافت پیام‌های SMS
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        // لغو ثبت رسیور در زمان مختصر
        unregisterReceiver(smsReceiver);
    }



    String Convert_DATE2(String day,String month,String year)
    {
        String temp_day="";
        String temp_mont="";
        if (day.length()==1)
        {
            temp_day="0"+day;
        }else{
            temp_day=day;
        }
        if (month.length()==1)
        {
            temp_mont="0"+month;
        }else{
            temp_mont=month;
        }





        return  (year+"/"+temp_mont+"/"+temp_day).toString();
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("سرویس پیامکی")
                .setContentText("در حال کار..")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);



        // نمایش اعلان با استفاده از NotificationManagerCompat

//        notificationManager.notify(NOTIFICATION_ID, builder.build());


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            startForeground(2, builder.build());
        } else {
            startForeground(2, builder.build(),
                    FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK);
        }















        smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                    SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                    for (SmsMessage smsMessage : messages) {



                        Thread thread=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    dataabse Temp= dataabse.getInstances(getApplicationContext());

                                    List<ReciveSms> NumbersR=Temp.ReciveSmsDaoAccess().GetReciveSms();




                                    if (!NumbersR.isEmpty())
                                    {
                                        String SmsNumber = smsMessage.getDisplayOriginatingAddress();
//

                                        if (SmsNumber.startsWith("+98"))
                                        {
                                            SmsNumber=SmsNumber.replace("+98","");
                                            if (!SmsNumber.startsWith("0"))
                                            {
                                                SmsNumber="0"+SmsNumber;
                                            }
                                        }

                                        if (SmsNumber.startsWith("98"))
                                        {
                                            SmsNumber=SmsNumber.replace("98","");
                                        }





                                         boolean Finded=false;
                                         ReciveSms FindedItem=new ReciveSms();

                                        for (int i=0;i<NumbersR.size();i++)
                                        {
                                            if (NumbersR.get(i).getNumber().trim().equals(SmsNumber))
                                            {
                                                Finded=true;
                                                FindedItem=NumbersR.get(i);
                                                break;
                                            }
                                        }

                                        if (Finded)
                                        {
                                            String messageBody = smsMessage.getDisplayMessageBody();
                                            SmsManager smsManager = SmsManager.getDefault();


                                            List<SendSms> NumbersS=Temp.SendSmsDaoAccess().GetSendSms();





                                            PersianDate persianDate = new PersianDate();



                                            for (int i=0;i<NumbersS.size();i++)
                                            {
                                                if (NumbersS.get(i).getIdNumberRecive().contains(FindedItem.getIddatabase()))
                                                {
                                                    smsManager.sendTextMessage(
                                                            NumbersS.get(i).getNumber(),
                                                            null,
                                                            messageBody,
                                                            null,
                                                            null
                                                    );

                                                    SendedSms sms2=new SendedSms();

                                                    String  currentDate=Convert_DATE2(String.valueOf(persianDate.getShDay())
                                                            ,String.valueOf(persianDate.getShMonth())
                                                            ,String.valueOf(persianDate.getShYear()));

                                                    sms2.setTime(persianDate.getHour() + ":" + persianDate.getMinute());
                                                    sms2.setDate(currentDate);
                                                    sms2.setIdNumberRecive(FindedItem.getNumber());
                                                    sms2.setIdNumberSend(NumbersS.get(i).getNumber());
                                                    Temp.SendedSmsDaoAccess().insertSendedSms(sms2);
                                                }


                                            }

                                        }

                                    }




                                } catch (Exception InterruptedException) {

                                }
                            }
                        });
                        thread.start();








                        // اینجا می‌توانید اقدامات مرتبط با دریافت پیام اس‌ام‌اس انجام دهید
                    }
                }
            }
        };








        IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(smsReceiver, filter);


//        while (true) {
//            // ایجاد یک تاخیر 3 ثانیه
//            try {
//                Thread.sleep(3000);
//                Log.i("dvsadvsv","NimaMoradi");
////                Toast.makeText(this, "این باریک هر 3 ثانیه یک بار نمایش داده می‌شود.", Toast.LENGTH_SHORT).show();
//
//            } catch (InterruptedException e) {
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//
//
//
//
//        }
      return  START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }






    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "SMS Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }
}
