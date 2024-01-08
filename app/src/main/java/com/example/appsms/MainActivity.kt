package com.example.appsms


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val smsManager = SmsManager.getDefault()

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewpagermain);
        tabLayout?.setupWithViewPager(viewPager)

        viewPager?.setAdapter(
            SampleFragmentPagerAdapter(
                supportFragmentManager,
                this@MainActivity
            )
        )

        tabLayout?.setupWithViewPager(viewPager);
        tabLayout?.getTabAt(0)?.setIcon(R.drawable.baseline_download_24)
        tabLayout?.getTabAt(1)?.setIcon(R.drawable.baseline_upload_24)

//        findViewById<Button>(R.id.Btn1).setOnClickListener {
//
//            for (i in 1..3) {
//                if (i == 1) {
//                    val parts = mutableListOf<String>()
//                    parts.add("تولدت مبارک!")
//                    parts.add("امیدوارم سال خوبی داشته باشی.")
//
//                    smsManager.sendMultipartTextMessage(
//                        "09169256596",
//                        null,
//                        parts as ArrayList<String>?,
//                        null,
//                        null
//                    )
//                }
//                if (i == 2) {
//                    val parts = mutableListOf<String>()
//                    parts.add("تولدت مبارک!")
//                    parts.add("امیدوارم سال خوبی داشته باشی.")
//
//                    smsManager.sendMultipartTextMessage(
//                        "09166561171",
//                        null,
//                        parts as ArrayList<String>?,
//                        null,
//                        null
//                    )
//                }
//                if (i == 3) {
//                    val parts = mutableListOf<String>()
//                    parts.add("تولدت مبارک!")
//                    parts.add("امیدوارم سال خوبی داشته باشی.")
//
//                    smsManager.sendMultipartTextMessage(
//                        "09167304068",
//                        null,
//                        parts as ArrayList<String>?,
//                        null,
//                        null
//                    )
//                }
//            }
//
//
//        }


//          val appPermission = arrayOf(android.Manifest.permission.READ_SMS, android.Manifest.permission.RECEIVE_MMS,android.Manifest.permission.FOREGROUND_SERVICE)
//          val appPermission = arrayOf(android.Manifest.permission.READ_SMS, android.Manifest.permission.RECEIVE_MMS,android.Manifest.permission.SEND_SMS)
//        ActivityCompat.requestPermissions(this,
//            arrayOf(android.Manifest.permission.SEND_SMS),
//            12)


//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
//            12
//        )



        // todo Start
//        val serviceIntent = Intent(this, SMSForegroundService::class.java)
//            startService(serviceIntent)
        // todo End



//        if (ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.POST_NOTIFICATIONS
//            )
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    this,
//                    android.Manifest.permission.POST_NOTIFICATIONS
//                )
//            ) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
//                    12
//                )
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        } else {
////            val intentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
////            registerReceiver(smsReceiver, intentFilter)
//            // برقراری اتصال به سرویس
//            // برقراری اتصال به سرویس
//            val serviceIntent = Intent(this, SMSForegroundService::class.java)
//            startService(serviceIntent)
//        }

    }

    override fun onDestroy() {
        super.onDestroy()


//        // لغو ثبت رسیور در اکتیویتی
//        unregisterReceiver(smsReceiver)
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