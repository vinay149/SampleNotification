package com.example.vinay_thakur.samplenotification;

import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by vinay_thakur on 6/24/2018.
 */

public class MessageReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)

    @Override
    public void onReceive(Context context, Intent intent) {
        getMessageText(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            Log.d("jj", (String) remoteInput.getCharSequence(MainActivity.key));
            return remoteInput.getCharSequence(MainActivity.key);

        }
        return null;
    }

}
