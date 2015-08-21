package com.kit.chisw.walkmancontrol.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.kit.chisw.walkmancontrol.ApiMessageManager;
import com.kit.chisw.walkmancontrol.R;
import com.kit.chisw.walkmancontrol.ReceiverUtil;
import com.kit.chisw.walkmancontrol.ui.views.RoundProgressView;
import com.kit.chisw.walkmancontrol.ui.views.RoundSpinnerView;
import com.kit.wear_connect_lib.model.TrackCompletedModel;
import com.kit.wear_connect_lib.model.TrackPausedModel;
import com.kit.wear_connect_lib.model.TrackPreparedModel;
import com.kit.wear_connect_lib.model.TrackSkippedModel;
import com.kit.wear_connect_lib.model.TrackStartedModel;
import com.kit.wear_connect_lib.model.commands.VolumeCommand;

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends GenericWatchActivity {

    private TextView mTrackName;
    private TextView mArtistName;
    private ImageView mBg;

    private ImageView mPlay;
    private ImageView mPrev;
    private ImageView mNext;

    private RoundSpinnerView mVolume;
    private RoundProgressView mRoundProgressView;

    private ApiMessageManager mApiMessageManager;

    private Object lastEvent;


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
        mTrackName = (TextView) stub.findViewById(R.id.track);
        mArtistName = (TextView) stub.findViewById(R.id.artist);
        mPlay = (ImageView) stub.findViewById(R.id.play);
        mPlay.setOnClickListener(clicker);
        mPrev = (ImageView) stub.findViewById(R.id.prev);
        mPrev.setOnClickListener(clicker);
        mNext = (ImageView) stub.findViewById(R.id.next);
        mNext.setOnClickListener(clicker);
        mVolume = (RoundSpinnerView) stub.findViewById(R.id.volume);
        mVolume.setProgressListener(new ProgressListener());
        mRoundProgressView = (RoundProgressView) findViewById(R.id.progress);
        mBg =  (ImageView) stub.findViewById(R.id.bg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiMessageManager = new ApiMessageManager(getApplicationContext());
        mApiMessageManager.setMessageListener(new MessageCallback());
        mApiMessageManager.addDataListener(new DataListener());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.play:
//                    mApiMessageManager.send("play");
//                    mApiMessageManager.send("get_volume");
//                    break;
//                case R.id.prev:
//                    mApiMessageManager.send("prev");
//                    mApiMessageManager.send("get_volume");
//                    break;
//                case R.id.next:
//                    mApiMessageManager.send("next");
//                    mApiMessageManager.send("get_volume");
//                    break;
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

    private class MessageCallback implements MessageApi.MessageListener {

        MessageListener mMessageListener = new MessageListener();

        @Override
        public void onMessageReceived(final MessageEvent pMessageEvent) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMessageListener.onMessageReceived(pMessageEvent);
                }
            });
        }

        private class MessageListener implements MessageApi.MessageListener {

            @Override
            public void onMessageReceived(MessageEvent pMessageEvent) {
                byte[] data = pMessageEvent.getData();
                Object model = ReceiverUtil.deserialize(data);
                int icon = android.R.drawable.ic_media_play;
                String track = "";
                String artist = "";

                if (model instanceof TrackCompletedModel) {
                    TrackCompletedModel m = (TrackCompletedModel) model;
                    track = m.getTrackName();
                    artist = m.getArtistName();
                    icon = R.drawable.selector_play;
                    mRoundProgressView.pause(m.getTrackPosition());
                    Log.d("oooof", "type " + TrackCompletedModel.class.getName());

                } else if (model instanceof TrackPausedModel) {
                    TrackPausedModel m = (TrackPausedModel) model;
                    track = m.getTrackName();
                    artist = m.getArtistName();
                    icon = R.drawable.selector_play;
                    mRoundProgressView.pause(m.getTrackPosition());
                    Log.d("oooof", "type " + TrackPausedModel.class.getName());

                } else if (model instanceof TrackPreparedModel) {
                    TrackPreparedModel m = (TrackPreparedModel) model;
                    track = m.getTrackName();
                    artist = m.getArtistName();
                    icon = R.drawable.selector_play;
                    mRoundProgressView.pause(0);
                    Log.d("oooof", "type " + TrackPreparedModel.class.getName());

                } else if (model instanceof TrackSkippedModel) {
                    TrackSkippedModel m = (TrackSkippedModel) model;
                    track = m.getTrackName();
                    artist = m.getArtistName();
                    icon = R.drawable.selector_play;
                    mRoundProgressView.pause(0);
                    Log.d("oooof", "type " + TrackSkippedModel.class.getName());

                } else if (model instanceof TrackStartedModel) {
                    TrackStartedModel m = (TrackStartedModel) model;
                    track = m.getTrackName();
                    artist = m.getArtistName();
                    icon = R.drawable.selector_pause;
                    mRoundProgressView.setDuration(m.getTrackDuration());
                    if (lastEvent != null && !(lastEvent instanceof TrackPausedModel)) {
                        mRoundProgressView.setPosition(0);
                    }
                    mRoundProgressView.start();
                    Log.d("oooof", "type " + TrackStartedModel.class.getName());
                } else if (model instanceof VolumeCommand) {
                    VolumeCommand m = (VolumeCommand) model;
                    final int vol = m.getVolume();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mVolume.setAngle(vol);
                        }
                    });

                    Log.d("oooof", "type " + VolumeCommand.class.getName());
                    return;
                }
                lastEvent = model;

                final int finalIcon = icon;
                final String finalTrack = track;
                final String finalArtist = artist;
                mTrackName.setText(finalTrack);
                mArtistName.setText(finalArtist);
                mPlay.setImageResource(finalIcon);
            }
        }
    }


    private class DataListener implements DataApi.DataListener {
        @Override
        public void onDataChanged(DataEventBuffer pDataEventBuffer) {
            for (DataEvent event : pDataEventBuffer) {
                if (event.getDataItem().getUri().getPath().equals("/image")) {
                    DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                    Asset profileAsset = dataMapItem.getDataMap().getAsset("profileImage");
                    final Bitmap bitmap = loadBitmapFromAsset(profileAsset);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBg.setImageBitmap(bitmap);
                        }
                    });

                }
            }
        }

        public Bitmap loadBitmapFromAsset(Asset asset) {
            if (asset == null) {
                throw new IllegalArgumentException("Asset must be non-null");
            }
            ConnectionResult result =
                    mApiMessageManager.getGoogleApiClient().blockingConnect(5000, TimeUnit.MILLISECONDS);
            if (!result.isSuccess()) {
                return null;
            }
            // convert asset into a file descriptor and block until it's ready
            InputStream assetInputStream = Wearable.DataApi.getFdForAsset(
                    mApiMessageManager.getGoogleApiClient(), asset).await().getInputStream();

            if (assetInputStream == null) {
                return null;
            }
            // decode the stream into a bitmap
            return BitmapFactory.decodeStream(assetInputStream);
        }
    }

}
