package com.aei.mazkekacontroller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationsManage {
    private static NotificationManager notificationManager;
    private static Context contx;
    private static String CHANNEL_ID = "com.distillery.aaa.mazkekacontroller.MAZKA";
    private static String CHANNEL_ID2 = "com.distillery.aaa.mazkekacontroller.MAZKALOW";

    public static void init(Context context, NotificationManager notif){
        contx = context;
        notificationManager = notif;
        createNotificationChannel();
    }
    //HIGH IMPORTANCE!!
    public static void noti (String temp, String sit){
        Intent i = new Intent(contx, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(contx,0,i,0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(contx,CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.launcher);
        mBuilder.setContentTitle("Mazkeka");
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setContentText(sit + " -  " + String.format(temp, "%.3f") + "C");
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        //mBuilder.setChannelId(channel2);
        notificationManager.notify(1, mBuilder.build());
    }

    public static void unoti(String temp, String sit) {
        Intent i = new Intent(contx, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(contx,0,i,0);

        NotificationCompat.Builder mBuilder2 = new NotificationCompat.Builder(contx,CHANNEL_ID2);
        mBuilder2.setSmallIcon(R.drawable.launcher);
        mBuilder2.setContentIntent(pendingIntent);
        mBuilder2.setContentTitle("Mazkeka");
        mBuilder2.setContentText(sit + " -  " + String.format(temp, "%.3f") + "C");
        //mBuilder2.setChannelId(channel2);
        notificationManager.notify(2, mBuilder2.build());
    }

    private static void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Main";
            String description = "Mazkeka main Notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.enableVibration(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);

            importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID2, name+"LOW", importance);
            channel2.setDescription(description+" LOW");
            channel2.enableLights(true);
            channel2.setSound(null,null);
            //channel2.enableVibration(true);
            notificationManager.createNotificationChannel(channel2);
        }
    }
}
