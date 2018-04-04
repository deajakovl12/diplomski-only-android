package com.androiddiplomski.device;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.androiddiplomski.R;
import com.androiddiplomski.ui.home.HomeActivity;
import com.androiddiplomski.util.Constants;

public class ForegroundService extends Service {

    private Handler trainingHandler = new Handler();
    private Intent intent;
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private boolean stopTimer = false;
    private LocationProvider fusedLocationProvider = null;
    private Location currentLocation = null;


    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(Constants.ACTION.BROADCAST_ACTION);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {

            fusedLocationProvider = new LocationProvider(getApplicationContext());
            fusedLocationProvider.startLocationUpdates();
            startTime = SystemClock.uptimeMillis();
            trainingHandler.postDelayed(updateTimerThread, 0);

            Intent notificationIntent = new Intent(this, HomeActivity.class);

            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);

            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("Snimanje u tijeku")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .build();

            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, notification);


        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            if (fusedLocationProvider != null) {
                fusedLocationProvider.stopLocationUpdates();
            }

            currentLocation = null;
            timeInMilliseconds = 0L;
            startTime = 0L;
            stopTimer = true;
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            intent.putExtra("timeInMilliseconds", timeInMilliseconds);
            /*intent.putExtra("distanceInMeters",distance);
            intent.putExtra("elevationGainInMeters",elevationGain);
            intent.putExtra("calories", calories);*/
            sendBroadcast(intent);
            if (!stopTimer) {
                trainingHandler.postDelayed(this, 0);
            }
        }
    };

    @Override
    public void onDestroy() {

        trainingHandler.removeCallbacks(updateTimerThread);
        if (fusedLocationProvider != null) {
            fusedLocationProvider.stopLocationUpdates();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
