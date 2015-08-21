package com.kit.wear_connect_lib.model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Yulia on 17.08.2015.
 */
public class TrackPausedModel implements Serializable {

    private static final String ALBUM_ID = "ALBUM_ID";
    private static final String ARTIST_ID = "ARTIST_ID";
    private static final String TRACK_URI = "TRACK_URI";
    private static final String ALBUM_NAME = "ALBUM_NAME";
    private static final String ARTIST_NAME = "ARTIST_NAME";
    private static final String TRACK_POSITION = "TRACK_POSITION";
    private static final String TRACK_NAME = "TRACK_NAME";
    private static final String TRACK_ID = "TRACK_ID";

    private int albumId;
    private int artistId;
    private String trackUri;
    private String albumName;
    private String artistName;
    private int trackPosition;
    private String trackName;
    private int trackId;

    public TrackPausedModel(Intent intent) {
        albumId= intent.getIntExtra(ALBUM_ID, -1);
        artistId= intent.getIntExtra(ARTIST_ID, -1);
        trackUri= intent.getStringExtra(TRACK_URI);
        albumName= intent.getStringExtra(ALBUM_NAME);
        artistName= intent.getStringExtra(ARTIST_NAME);
        trackPosition= intent.getIntExtra(TRACK_POSITION, -1);
        trackName= intent.getStringExtra(TRACK_NAME);
        trackId= intent.getIntExtra(TRACK_ID, -1);
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
