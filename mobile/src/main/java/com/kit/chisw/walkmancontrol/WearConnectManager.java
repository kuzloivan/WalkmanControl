package com.kit.chisw.walkmancontrol;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.kit.wear_connect_lib.model.TrackStartedModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;

import util.SerialiserUtil;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class WearConnectManager {

    private GoogleApiClient googleApiClient;
    private String action;
    private Object data;

    public WearConnectManager(Context context, String pAction, Object pData) {
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
                                googleApiClient, f.getId(), action, SerialiserUtil.serialize(data)).await();

                    }

                    if(data instanceof TrackStartedModel){
                        s(googleApiClient.getContext(),((TrackStartedModel) data).getAlbumId());
                    }

                }
            });
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        private void s(Context pContext,int id){
            Cursor cursor = pContext.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                    MediaStore.Audio.Albums._ID+ "=?",
                    new String[] {String.valueOf(id)},
                    null);

            if (cursor.moveToFirst()) {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeFile(path);
                if(bitmap==null){
                    return;
                }
                try {
                    Wearable.DataApi.putDataItem(googleApiClient, createAssetFromBitmap(bitmap).asPutDataRequest()).await();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




        }
        private PutDataMapRequest createAssetFromBitmap(Bitmap bitmap) throws IOException {
            PutDataMapRequest putRequest = PutDataMapRequest.create("/image");
            DataMap map = putRequest.getDataMap();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.flush();
            byte[] byteArray = stream.toByteArray();
            Asset asset = Asset.createFromBytes(byteArray);
            map.putAsset("profileImage", asset);
            map.putLong("dataSize", byteArray.length);
            return putRequest;
        }

    }
}
