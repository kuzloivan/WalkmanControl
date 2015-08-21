package com.kit.chisw.walkmancontrol;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.kit.chisw.walkmancontrol.ui.activity.MainActivity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class ApiMessageManager {
    private GoogleApiClient mGoogleApiClient;
    private Executor mExecutor;

    private List<Node> mNodes;

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public ApiMessageManager(Context pContext) {
        mGoogleApiClient = new GoogleApiClient.Builder(pContext).addApi(Wearable.API)
                .build();
        mGoogleApiClient.connect();
        mExecutor = Executors.newSingleThreadExecutor();
    }


    public void setMessageListener(MessageApi.MessageListener pListener) {

        Wearable.MessageApi.addListener(mGoogleApiClient, pListener);

    }

    public void send(final String text) {
        mExecutor.execute(new ApiCommand("Music", text));
    }

    public void addDataListener(DataApi.DataListener pDataListener) {
        Wearable.DataApi.addListener(mGoogleApiClient,pDataListener);
    }

    private class ApiCommand implements Runnable {
        private String action;
        private String data;

        public ApiCommand(String pAction, String pData) {
            action = pAction;
            data = pData;
        }

        @Override
        public void run() {
            if (mNodes == null) {
                mNodes = Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await().getNodes();

            }
            for (Node node : mNodes) {

                Wearable.MessageApi.sendMessage(
                        mGoogleApiClient, node.getId(), action, data.getBytes()).await();
                Log.d("OOOOO", "id:  + " + node.getId());
            }

        }
    }


}
