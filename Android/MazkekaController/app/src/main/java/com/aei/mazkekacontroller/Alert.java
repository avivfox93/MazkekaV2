package com.aei.mazkekacontroller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;

public class Alert extends Fragment{

    private static final String[] ALERT_TITLE = {
            "Stop Distilling", "Exit", "Cannot connect!"
    };
    private static final String ALERT_MSG = "Are you sure?";
    public static final int STOP = 0;
    public static final int EXIT = 1;
    public static final int CANT_CONNECT = 2;

    public static Dialog createDialog(Context cntx, int alert, callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(cntx);
        builder.setTitle(ALERT_TITLE[alert]);
        builder.setMessage(ALERT_MSG);
        builder.setCancelable(true);
        builder.setPositiveButton("YES",(d, i) -> {
            callback.callBack();
            d.dismiss();
        });
        builder.setNegativeButton("NO",(dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        return builder.create();
    }

    public static Dialog createDialog(Context cntx, int alert) {
        AlertDialog.Builder builder = new AlertDialog.Builder(cntx);
        builder.setTitle(ALERT_TITLE[alert]);
        builder.setMessage(ALERT_MSG);
        builder.setCancelable(true);
        builder.setPositiveButton("YES", (d, i) -> {
            d.dismiss();
        });
        builder.setNegativeButton("NO",(d, i) -> {
            d.dismiss();
        });
        return builder.create();
    }

    interface callback{
        void callBack();
    }
}
