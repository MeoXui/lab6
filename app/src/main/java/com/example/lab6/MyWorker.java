package com.example.lab6;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context,workerParameters);
    }

    @NonNull
    @Override
    public Result doWork() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            Toast.makeText(getApplicationContext(), "Đang sạc", Toast.LENGTH_SHORT).show();
        }, 1000);
        return Result.success();
    }
}
