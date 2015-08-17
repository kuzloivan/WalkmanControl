package com.kit.chisw.walkmancontrol.model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Yulia on 17.08.2015.
 */
public class TrackSkippedModel implements Serializable {

    private static final String ALBUM_NAME = "ALBUM_NAME";
    private static final String ARTIST_NAME = "ARTIST_NAME";
    private static final String TRACK_POSITION = "TRACK_POSITION";
    private static final String TRACK_NAME = "TRACK_NAME";

    private String albumName;
    private String artistName;
    private String trackName;
    private int trackPosition;

    public TrackSkippedModel(Intent intent) {
        albumName = intent.getStringExtra(ALBUM_NAME);
        artistName = intent.getStringExtra(ARTIST_NAME);
        trackPosition = intent.getIntExtra(TRACK_POSITION, 0);
        trackName = intent.getStringExtra(TRACK_NAME);
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(int trackPosition) {
        this.trackPosition = trackPosition;
    }
}
