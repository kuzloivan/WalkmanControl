package com.kit.wear_connect_lib.model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Yulia on 17.08.2015.
 */
public class TrackSkippedModel implements Serializable {

    private static String ALBUM_ID = "ALBUM_ID";
    private static String TRACK_DURATION = "TRACK_DURATION";
    private static String ARTIST_ID = "ARTIST_ID";
    private static String TRACK_URI = "TRACK_URI";
    private static String ALBUM_NAME = "ALBUM_NAME";
    private static String ARTIST_NAME = "ARTIST_NAME";
    private static String TRACK_POSITION = "TRACK_POSITION";
    private static String TRACK_NAME = "TRACK_NAME";
    private static String TRACK_ID = "TRACK_ID";


    private int albumId;
    private int trackDuration;
    private int artistId;
    private String trackUri;
    private String albumName;
    private String artistName;
    private int trackPosition;
    private String trackName;
    private int trackId;

    public TrackSkippedModel(Intent pIntent) {
        albumId= pIntent.getIntExtra(ALBUM_ID, -1);
        trackDuration= pIntent.getIntExtra(TRACK_DURATION, -1);
        artistId= pIntent.getIntExtra(ARTIST_ID, -1);
        trackUri= pIntent.getStringExtra(TRACK_URI);
        albumName= pIntent.getStringExtra(ALBUM_NAME);
        artistName= pIntent.getStringExtra(ARTIST_NAME);
        trackPosition= pIntent.getIntExtra(TRACK_POSITION, -1);
        trackName= pIntent.getStringExtra(TRACK_NAME);
        trackId= pIntent.getIntExtra(TRACK_ID, -1);
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getTrackDuration() {
        return trackDuration;
    }

    public int getArtistId() {
        return artistId;
    }

    public String getTrackUri() {
        return trackUri;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getTrackPosition() {
        return trackPosition;
    }

    public String getTrackName() {
        return trackName;
    }

    public int getTrackId() {
        return trackId;
    }
}
