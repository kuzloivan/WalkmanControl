package com.kit.chisw.walkmancontrol.ui.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.kit.chisw.walkmancontrol.ApiMessageManager;
import com.kit.chisw.walkmancontrol.R;
import com.kit.chisw.walkmancontrol.ui.views.RoundSpinnerView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends GenericWatchActivity {

    private static final String ALBUM_ID = "ALBUM_ID";
    private static final String ARTIST_ID = "ARTIST_ID";
    private static final String TRACK_URI = "TRACK_URI";
    private static final String ALBUM_NAME = "ALBUM_NAME";
    private static final String ARTIST_NAME = "ARTIST_NAME";
    private static final String TRACK_POSITION = "TRACK_POSITION";
    private static final String TRACK_NAME = "TRACK_NAME";
    private static final String TRACK_ID = "TRACK_ID";
    private static final String IS_LOCAL = "IS_LOCAL";
    private static final String TRACK_DURATION = "TRACK_DURATION";
    private static final String EVENT_TYPE = "TYPE";

    private static final String TYPE_TRACK_COMPLETED = "TRACK_COMPLETED";
    private static final String TYPE_TRACK_PREPARED = "TRACK_PREPARED";
    private static final String TYPE_ACTION_TRACK_STARTED = "ACTION_TRACK_STARTED";
    private static final String TYPE_ACTION_PAUSED = "ACTION_PAUSED";
    private static final String TYPE_ACTION_SKIPPED = "ACTION_SKIPPED";
    private static final String TYPE_PLAYBACK_ERROR = "PLAYBACK_ERROR";

    private TextView mTextView;
    private ImageView mPlay;
    private ImageView mPrev;
    private ImageView mNext;

    RoundSpinnerView roundSpinnerView;

    private ApiMessageManager mApiMessageManager;


    @Override
    public int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onLayoutInflated(WatchViewStub watchViewStub) {
        initView(watchViewStub);
    }

    private void initView(WatchViewStub stub) {
        Clicker clicker = new Clicker();
        mTextView = (TextView) stub.findViewById(R.id.text);
        mPlay = (ImageView) stub.findViewById(R.id.play);
        mPlay.setOnClickListener(clicker);
        mPrev = (ImageView) stub.findViewById(R.id.prev);
        mPrev.setOnClickListener(clicker);
        mNext = (ImageView) stub.findViewById(R.id.next);
        mNext.setOnClickListener(clicker);
        roundSpinnerView = (RoundSpinnerView) stub.findViewById(R.id.volume);
        roundSpinnerView.setProgressListener(new ProgressListener());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)){
            Log.d("GPS","true");
        }else {
            Log.d("GPS","false");
        }
        mApiMessageManager = new ApiMessageManager(getApplicationContext());
        mApiMessageManager.setMessageListener(new MessageListener());
    }

    private class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play:
                    mApiMessageManager.send("play");
                    mApiMessageManager.send("get_volume");
                    break;
                case R.id.prev:
                    mApiMessageManager.send("prev");
                    mApiMessageManager.send("get_volume");
                    break;
                case R.id.next:
                    mApiMessageManager.send("next");
                    mApiMessageManager.send("get_volume");
                    break;
            }
        }
    }

    private class ProgressListener implements RoundSpinnerView.ProgressListener {
        @Override
        public void onProgressChange(int progress) {
            mApiMessageManager.send("volume " + progress);
        }

        @Override
        public void onChange(int progress) {


        }
    }

    private class MessageListener implements MessageApi.MessageListener {

        @Override
        public void onMessageReceived(MessageEvent pMessageEvent) {
            JSONObject track = new JSONObject();
            String s = new String(pMessageEvent.getData());
            Log.d("oooo", s);

            try {
                track = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
               roundSpinnerView.setAngle(2.7*Integer.parseInt(s));
                return;
            }
            Log.d("oooof", track.optString(EVENT_TYPE));

            String text = "";
            int icon = android.R.drawable.ic_media_play;
            switch (track.optString(EVENT_TYPE)) {
                case TYPE_TRACK_COMPLETED:
                    text = track.optString(ARTIST_NAME) + " - " + track.optString(TRACK_NAME);
                    icon = android.R.drawable.ic_media_play;
                    break;
                case TYPE_TRACK_PREPARED:
                    text = track.optString(ARTIST_NAME) + " - " + track.optString(TRACK_NAME);
                    icon = android.R.drawable.ic_media_play;
                    break;
                case TYPE_ACTION_TRACK_STARTED:
                    text = track.optString(ARTIST_NAME) + " - " + track.optString(TRACK_NAME);
                    icon = android.R.drawable.ic_media_pause;
                    break;
                case TYPE_ACTION_PAUSED:
                    text = track.optString(ARTIST_NAME) + " - " + track.optString(TRACK_NAME);
                    icon = android.R.drawable.ic_media_play;
                    break;
                case TYPE_ACTION_SKIPPED:
                    text = track.optString(ARTIST_NAME) + " - " + track.optString(TRACK_NAME);
                    icon = android.R.drawable.ic_media_play;
                    break;
                case TYPE_PLAYBACK_ERROR:
                    break;
            }
            final String finalText = text;
            Log.d("oooof", track.optString(ARTIST_NAME));
            final int finalIcon = icon;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText(finalText);
                    mPlay.setImageResource(finalIcon);
                }
            });
        }
    }
}
