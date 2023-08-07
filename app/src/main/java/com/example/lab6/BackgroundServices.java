package com.example.lab6;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BackgroundServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Đang chạy.", Toast.LENGTH_SHORT).show();
        try {
            new Handler().postDelayed(() -> {
                Toast.makeText(this, "Đang chuyển.", Toast.LENGTH_SHORT).show();
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse("https://caodang.fpt.edu.vn/"));
                web.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(web);
                stopSelf();
            },5000);
        } catch (Exception e) {
            Toast.makeText(this, "Đã có lỗi xẩy ra.", Toast.LENGTH_SHORT).show();
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Dừng.", Toast.LENGTH_SHORT).show();
    }
}
