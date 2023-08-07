package com.example.lab6;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_send).setOnClickListener(v -> {
            Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.fpolylogo);

            NotificationCompat.Builder builder = new NotificationCompat
                    .Builder(this, ConfigNotification.CHANNEL_ID)
                    .setSmallIcon(R.mipmap.fpticon)
                    .setContentTitle("Chào!")
                    .setContentText("Android 2")
                    .setStyle(new NotificationCompat
                            .BigPictureStyle()
                            .bigPicture(logo)
                            .bigLargeIcon(null)
                    )
                    .setLargeIcon(logo)
                    .setColor(Color.RED)
                    .setAutoCancel(true);

            NotificationManagerCompat nmc = NotificationManagerCompat.from(this);

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED)
                nmc.notify((int) new Date().getTime(), builder.build());
            else
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.POST_NOTIFICATIONS}, 7979);
        });

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForegroundServic.class);
            startService(intent);
        });

        findViewById(R.id.btn_stop).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForegroundServic.class);
            stopService(intent);
        });

        findViewById(R.id.btn_start_background).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BackgroundServices.class);
            startService(intent);
        });

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(MainActivity.this).enqueue(workRequest);
    }
}