package com.kit.chisw.walkmancontrol;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.concurrent.Executors;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class WearConnectManager {

    private GoogleApiClient googleApiClient;
    private String action;
    private String data;

    public WearConnectManager(Context context, String pAction, String pData) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(new ConnectionListener())
                .addApi(Wearable.API)
                .build();
        action = pAction;
        data = pData;
    }

    public void send(){
        googleApiClient.connect();
    }

    private class ConnectionListener implements  GoogleApiClient.ConnectionCallbacks{

        @Override
        public void onConnected(Bundle pBundle) {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( googleApiClient ).await();
                    for(Node node : nodes.getNodes()) {
                        final Node f = node;
                        if(node.getId().equals("cloud")){
                            continue;
                        }
                        Wearable.MessageApi.sendMessage(
                                googleApiClient, f.getId(), action, data.getBytes()).await();


                    }

                }
            });
        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    }
}
