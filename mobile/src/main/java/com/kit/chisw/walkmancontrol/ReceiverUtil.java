package com.kit.chisw.walkmancontrol;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class ReceiverUtil {

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


    public static JSONObject parseWalkmanIntent(Intent intent) {
        JSONObject jsonObject = new JSONObject();

        switch (intent.getAction()) {
            case "com.sonyericsson.music.TRACK_COMPLETED": return parseTrackCompleted(intent);
            case "com.sonyericsson.music.TRACK_PREPARED": return parsePrepared(intent);
            case "com.sonyericsson.music.playbackcontrol.ACTION_TRACK_STARTED": return parseTrackStarted(intent);
            case "com.sonyericsson.music.playbackcontrol.ACTION_PAUSED": return parseTrackPaused(intent);
            case "com.sonyericsson.music.playbackcontrol.ACTION_SKIPPED": return parseSkipped(intent);
            case "com.sonyericsson.music.PLAYBACK_ERROR": return parsePlayBackError(intent);

        }
        return null;
    }


    private static JSONObject parseTrackCompleted(Intent intent){
        JSONObject track = new JSONObject();
        try {
            track.put(EVENT_TYPE,TYPE_TRACK_COMPLETED);
            track.put(ALBUM_NAME,intent.getStringExtra(ALBUM_NAME));
            track.put(ARTIST_NAME,intent.getStringExtra(ARTIST_NAME));
            track.put(TRACK_POSITION,intent.getIntExtra(TRACK_POSITION, 0));
            track.put(TRACK_NAME,intent.getStringExtra(TRACK_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }
    private static JSONObject parsePrepared(Intent intent){
        JSONObject track = new JSONObject();
        try {
            track.put(EVENT_TYPE, TYPE_TRACK_PREPARED);
            track.put(ALBUM_NAME,intent.getStringExtra(ALBUM_NAME));
            track.put(ARTIST_NAME,intent.getStringExtra(ARTIST_NAME));
            track.put(TRACK_NAME,intent.getStringExtra(TRACK_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }
    private static JSONObject parseTrackStarted(Intent intent){
        JSONObject track = new JSONObject();
        try {
            track.put(EVENT_TYPE,TYPE_ACTION_TRACK_STARTED);
            track.put(ALBUM_NAME,intent.getStringExtra(ALBUM_NAME));
            track.put(ARTIST_NAME,intent.getStringExtra(ARTIST_NAME));
            track.put(TRACK_DURATION,intent.getIntExtra(TRACK_DURATION, 0));
            track.put(TRACK_NAME,intent.getStringExtra(TRACK_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }
    private static JSONObject parseTrackPaused(Intent intent){
        JSONObject track = new JSONObject();
        try {
            track.put(EVENT_TYPE,TYPE_ACTION_PAUSED);
            track.put(ALBUM_NAME,intent.getStringExtra(ALBUM_NAME));
            track.put(ARTIST_NAME,intent.getStringExtra(ARTIST_NAME));
            track.put(TRACK_POSITION,intent.getIntExtra(TRACK_POSITION, 0));
            track.put(TRACK_NAME,intent.getStringExtra(TRACK_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }
    private static JSONObject parseSkipped(Intent intent){
        JSONObject track = new JSONObject();
        try {
            track.put(EVENT_TYPE,TYPE_ACTION_SKIPPED);
            track.put(ALBUM_NAME,intent.getStringExtra(ALBUM_NAME));
            track.put(ARTIST_NAME,intent.getStringExtra(ARTIST_NAME));
            track.put(TRACK_DURATION,intent.getIntExtra(TRACK_DURATION, 0));
            track.put(TRACK_POSITION,intent.getIntExtra(TRACK_POSITION, 0));
            track.put(TRACK_NAME,intent.getStringExtra(TRACK_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }
    private static JSONObject parsePlayBackError(Intent intent){
        JSONObject track = new JSONObject();
        try {
            track.put(EVENT_TYPE,TYPE_PLAYBACK_ERROR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }
}
