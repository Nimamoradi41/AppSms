package com.example.appsms


import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private var materialSwitch: MaterialSwitch? = null




    fun CheckPermisionsApp() : ArrayList<Int>{
        var i=ArrayList<Int>();


        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.FOREGROUND_SERVICE)!=PackageManager.PERMISSION_GRANTED)
        {
            i.add(1)
        }

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)
        {
            i.add(2)

        }

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED)
        {
            i.add(3)

        }

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            i.add(4)

        }

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            i.add(5)

        }


        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            i.add(6)

        }

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK)!=PackageManager.PERMISSION_GRANTED)
        {
            i.add(7)

        }





        return  i

    }

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val smsManager = SmsManager.getDefault()

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewpagermain);
        materialSwitch=findViewById(R.id.materialSwitch);
        tabLayout?.setupWithViewPager(viewPager)

        viewPager?.setAdapter(
            SampleFragmentPagerAdapter(
                supportFragmentManager,
                this@MainActivity,this
            )
        )


        materialSwitch?.isChecked = isMyServiceRunning(SMSForegroundService::class.java)

        tabLayout?.setupWithViewPager(viewPager);
        tabLayout?.getTabAt(0)?.setIcon(R.drawable.baseline_download_24)
        tabLayout?.getTabAt(1)?.setIcon(R.drawable.baseline_upload_24)
        tabLayout?.getTabAt(2)?.setIcon(R.drawable.baseline_send_24)


        materialSwitch?.setOnClickListener {
            var Per=CheckPermisionsApp()
            var ddd=materialSwitch?.isChecked
            if (materialSwitch?.isChecked!!)
            {
                var Per=CheckPermisionsApp()
                if (Per.size<=1)
                {
                    materialSwitch?.isChecked=true
                    val serviceIntent = Intent(this, SMSForegroundService::class.java)
                    startService(serviceIntent)
                }else{
                    materialSwitch?.isChecked=false
                    requestPermissions(arrayOf(
                        android.Manifest.permission.POST_NOTIFICATIONS,
                        android.Manifest.permission.FOREGROUND_SERVICE,
                        android.Manifest.permission.READ_PHONE_STATE,
                        android.Manifest.permission.READ_SMS,
                        android.Manifest.permission.SEND_SMS,
                        android.Manifest.permission.RECEIVE_SMS,
                        android.Manifest.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK,
                    ),7)

                }
            }else{
                materialSwitch?.isChecked=false
                stopService(Intent(this,SMSForegroundService::class.java))
            }
        }





    }

    override fun onDestroy() {
        super.onDestroy()


//        // لغو ثبت رسیور در اکتیویتی
//        unregisterReceiver(smsReceiver)
    }


    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}


//    private val CHANNEL_ID = "my_service_channel"
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            ActivityCompat.requestPermissions(this,
//                arrayOf(android.Manifest.permission.FOREGROUND_SERVICE),
//                12)
//        }
//        findViewById<Button>(R.id.Btn1).setOnClickListener {
//            startForegroundService(Intent(this, MyService2::class.java))
//        }
//
////        findViewById<Button>(R.id.stop_service).setOnClickListener {
////            stopService(Intent(this, MyService::class.java))
////        }
//    }
//}
//
//class MyService2 : Service() {
//
//    var i=1;
//    private val CHANNEL_ID = "my_service_channel"
//
//    override fun onCreate() {
//        super.onCreate()
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val CHANNEL_ID = "my_channel_01"
//            val channel = NotificationChannel(
//                CHANNEL_ID,
//                "Channel human readable title",
//                NotificationManager.IMPORTANCE_MIN
//            )
//            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
//                channel
//            )
//
//            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("EEEEEEE")
//                .setContentText("").build()
//
//            startForeground(85, notification)
//        }
//
//
//
//
//
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//
//
//
//
//
//        return START_STICKY
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//}