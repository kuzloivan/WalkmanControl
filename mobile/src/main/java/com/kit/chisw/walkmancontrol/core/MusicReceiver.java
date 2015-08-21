package com.kit.chisw.walkmancontrol.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.kit.chisw.walkmancontrol.ReceiverUtil;
import com.kit.chisw.walkmancontrol.WearConnectManager;

import java.util.Arrays;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class MusicReceiver extends BroadcastReceiver {
    private GoogleApiClient googleApiClient;
    @Override
    public void onReceive(Context context, final Intent intent) {
        Object data = ReceiverUtil.parseWalkmanIntent(intent, context);
        if(data==null){
            return;
        }
        new WearConnectManager(context, "TRACK",data ).send();
        Log.d(intent.getAction(), Arrays.toString(intent.getExtras().keySet().toArray()));
    }


}
