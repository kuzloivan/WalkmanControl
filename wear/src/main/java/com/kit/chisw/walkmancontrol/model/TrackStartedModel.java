package com.kit.chisw.walkmancontrol.model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Yulia on 17.08.2015.
 */
public class TrackStartedModel implements Serializable {

    private static final String ALBUM_NAME = "ALBUM_NAME";
    private static final String ARTIST_NAME = "ARTIST_NAME";
    private static final String TRACK_DURATION = "TRACK_DURATION";
    private static final String TRACK_NAME = "TRACK_NAME";

    private String albumName;
    private String artistName;
    private String trackName;
    private int trackDuration;

    public TrackStartedModel(Intent intent) {
        albumName = intent.getStringExtra(ALBUM_NAME);
        artistName = intent.getStringExtra(ARTIST_NAME);
        trackName = intent.getStringExtra(TRACK_NAME);
        trackDuration = intent.getIntExtra(TRACK_DURATION, 0);
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

    public int getTrackDuration() {
        return trackDuration;
    }

    public void setTrackDuration(int trackDuration) {
        this.trackDuration = trackDuration;
    }
}
