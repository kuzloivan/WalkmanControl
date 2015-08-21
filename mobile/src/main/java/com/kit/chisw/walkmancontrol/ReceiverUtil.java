package com.kit.chisw.walkmancontrol;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.MediaStore;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.kit.wear_connect_lib.model.TrackCompletedModel;
import com.kit.wear_connect_lib.model.TrackPausedModel;
import com.kit.wear_connect_lib.model.TrackPreparedModel;
import com.kit.wear_connect_lib.model.TrackSkippedModel;
import com.kit.wear_connect_lib.model.TrackStartedModel;
import com.kit.wear_connect_lib.model.commands.VolumeCommand;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import util.SerialiserUtil;


/**
 * Created by Kuzlo on 08.07.2015.
 */
public class ReceiverUtil {

    private static final String TYPE_TRACK_COMPLETED = "TRACK_COMPLETED";
    private static final String TYPE_TRACK_PREPARED = "TRACK_PREPARED";
    private static final String TYPE_ACTION_TRACK_STARTED = "ACTION_TRACK_STARTED";
    private static final String TYPE_ACTION_PAUSED = "ACTION_PAUSED";
    private static final String TYPE_ACTION_SKIPPED = "ACTION_SKIPPED";
    private static final String TYPE_PLAYBACK_ERROR = "PLAYBACK_ERROR";


    public static Object parseWalkmanIntent(Intent intent,Context pContext) {

        switch (intent.getAction()) {
            case "com.sonyericsson.music.TRACK_COMPLETED":
                return new TrackCompletedModel(intent);
            case "com.sonyericsson.music.TRACK_PREPARED":
                return new TrackPreparedModel(intent);
            case "com.sonyericsson.music.playbackcontrol.ACTION_TRACK_STARTED":

                return new TrackStartedModel(intent);
            case "com.sonyericsson.music.playbackcontrol.ACTION_PAUSED":
                return new TrackPausedModel(intent);
            case "com.sonyericsson.music.playbackcontrol.ACTION_SKIPPED":
                return new TrackSkippedModel(intent);
            case "android.media.VOLUME_CHANGED_ACTION":
                AudioManager am = (AudioManager) pContext.getSystemService(Context.AUDIO_SERVICE);
                if((Integer)intent.getExtras().get("android.media.EXTRA_VOLUME_STREAM_TYPE")!=AudioManager.STREAM_MUSIC){
                    return null;
                }
                return new VolumeCommand(intent,am);

        }
        return null;
    }






}
