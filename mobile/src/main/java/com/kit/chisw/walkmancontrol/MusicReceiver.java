package com.kit.chisw.walkmancontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class MusicReceiver extends BroadcastReceiver {
    private GoogleApiClient googleApiClient;
    @Override
    public void onReceive(Context context, final Intent intent) {
        new WearConnectManager(context,"TRACK",ReceiverUtil.parseWalkmanIntent(intent).toString()).send();
    }


}
