package com.kit.wear_connect_lib.model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Yulia on 17.08.2015.
 */
public class TrackPreparedModel implements Serializable {

    private static final String ALBUM_ID = "ALBUM_ID";
    private static final String ARTIST_ID = "ARTIST_ID";
    private static final String TRACK_URI = "TRACK_URI";
    private static final String ALBUM_NAME = "ALBUM_NAME";
    private static final String ARTIST_NAME = "ARTIST_NAME";
    private static final String TRACK_NAME = "TRACK_NAME";
    private static final String IS_LOCAL = "IS_LOCAL";
    private static final String TRACK_ID = "TRACK_ID";


    private int albumId;
    private int artistId;
    private String trackUri;
    private String albumName;
    private String artistName;
    private String trackName;
    private boolean isLocal;
    private int trackId;

    public TrackPreparedModel(Intent pIntent) {
        albumId= pIntent.getIntExtra(ALBUM_ID,-1);
        artistId= pIntent.getIntExtra(ARTIST_ID,-1);
        trackUri= pIntent.getStringExtra(TRACK_URI);
        albumName= pIntent.getStringExtra(ALBUM_NAME);
        artistName= pIntent.getStringExtra(ARTIST_NAME);
        trackName= pIntent.getStringExtra(TRACK_NAME);
        isLocal= pIntent.getBooleanExtra(IS_LOCAL,true);
        trackId= pIntent.getIntExtra(TRACK_ID,-1);
    }

    public int getAlbumId() {
        return albumId;
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

    public String getTrackName() {
        return trackName;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public int getTrackId() {
        return trackId;
    }
}
