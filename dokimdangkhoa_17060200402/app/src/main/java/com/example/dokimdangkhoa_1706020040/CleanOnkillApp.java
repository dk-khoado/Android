package com.example.dokimdangkhoa_1706020040;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

@SuppressLint("Registered")
public class CleanOnkillApp extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        LoginActivity.mGoogleSignInClient.signOut();
        stopSelf();
    }
}
