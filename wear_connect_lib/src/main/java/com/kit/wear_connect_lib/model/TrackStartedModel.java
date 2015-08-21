package com.kit.wear_connect_lib.model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Yulia on 17.08.2015.
 */
public class TrackStartedModel implements Serializable {

    private static final String ALBUM_ID = "ALBUM_ID";
    private static final String TRACK_DURATION = "TRACK_DURATION";
    private static final String ARTIST_ID = "ARTIST_ID";
    private static final String TRACK_URI = "TRACK_URI";
    private static final String ALBUM_NAME = "ALBUM_NAME";
    private static final String ARTIST_NAME = "ARTIST_NAME";
    private static final String TRACK_NAME = "TRACK_NAME";
    private static final String TRACK_ID = "TRACK_ID";


    private int albumId;
    private int trackDuration;
    private int artistId;
    private String trackUri;
    private String albumName;
    private String artistName;
    private String trackName;
    private int trackId;

    public TrackStartedModel(Intent pIntent) {
        albumId= pIntent.getIntExtra(ALBUM_ID, -1);
        trackDuration= pIntent.getIntExtra(TRACK_DURATION, -1);
        artistId= pIntent.getIntExtra(ARTIST_ID, -1);
        trackUri= pIntent.getStringExtra(TRACK_URI);
        albumName= pIntent.getStringExtra(ALBUM_NAME);
        artistName= pIntent.getStringExtra(ARTIST_NAME);
        trackName= pIntent.getStringExtra(TRACK_NAME);
        trackId= pIntent.getIntExtra(TRACK_ID, -1);
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int pAlbumId) {
        albumId = pAlbumId;
    }

    public int getTrackDuration() {
        return trackDuration;
    }

    public void setTrackDuration(int pTrackDuration) {
        trackDuration = pTrackDuration;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int pArtistId) {
        artistId = pArtistId;
    }

    public String getTrackUri() {
        return trackUri;
    }

    public void setTrackUri(String pTrackUri) {
        trackUri = pTrackUri;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String pAlbumName) {
        albumName = pAlbumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String pArtistName) {
        artistName = pArtistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String pTrackName) {
        trackName = pTrackName;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int pTrackId) {
        trackId = pTrackId;
    }
}
