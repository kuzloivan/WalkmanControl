package com.kit.chisw.walkmancontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {
    GoogleApiClient googleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ConnectionListener listener = new ConnectionListener();
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(listener)
//                .addOnConnectionFailedListener(listener)
//                .addApi(Wearable.API)
//                .build();
//
//        googleApiClient.connect();


    }

//    private class ConnectionListener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//
//        @Override
//        public void onConnected(Bundle pBundle) {
//
//
//            Wearable.MessageApi.addListener(googleApiClient, new MessageApi.MessageListener() {
//                @Override
//                public void onMessageReceived(MessageEvent pMessageEvent) {
//                    Intent i;
//                    switch (new String(pMessageEvent.getData())) {
//                        case "next":
//                             i = new Intent(Intent.ACTION_MEDIA_BUTTON);
//                            synchronized (this) {
//                                i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_NEXT));
//                                sendOrderedBroadcast(i, null);
//
//                                i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_NEXT));
//                                sendOrderedBroadcast(i, null);
//                            }
//                            break;
//                        case "prev":
//                             i = new Intent(Intent.ACTION_MEDIA_BUTTON);
//                            synchronized (this) {
//                                i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PREVIOUS));
//                                sendOrderedBroadcast(i, null);
//
//                                i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PREVIOUS));
//                                sendOrderedBroadcast(i, null);
//                            }
//                            break;
//                        case "pause":
//                             i = new Intent(Intent.ACTION_MEDIA_BUTTON);
//                            synchronized (this) {
//                                i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE));
//                                sendOrderedBroadcast(i, null);
//
//                                i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE));
//                                sendOrderedBroadcast(i, null);
//                            }
//                            break;
//                    }
//                }
//            });
//
//
//        }
//
//        @Override
//        public void onConnectionSuspended(int i) {
//
//        }
//
//
//        @Override
//        public void onConnectionFailed(ConnectionResult pConnectionResult) {
//
//        }
//    }


}
