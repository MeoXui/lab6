package com.example.lab6;

import static com.example.lab6.ConfigNotification.CHANNEL_ID;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundServic extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Đang chạy.", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.fpticon)
                .setContentTitle("Đang chạy.")
                .setContentText("Không tắt thông báo này bằng cách vuốt bằng cách lưới được.")
                .setColor(Color.RED);

        Notification notification = builder.build();

        startForeground(1,notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Dừng.", Toast.LENGTH_SHORT).show();
    }
}
