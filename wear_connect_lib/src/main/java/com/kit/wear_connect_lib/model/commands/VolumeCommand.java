package com.kit.wear_connect_lib.model.commands;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import java.io.Serializable;

/**
 * Created by Kuzlo on 20.08.2015.
 */
public class VolumeCommand implements Serializable{

    public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";

    private int volume;
    private int volumeLevel;

    public VolumeCommand(int pVolume) {
        volume = pVolume;
    }

    public VolumeCommand(Intent pIntent,AudioManager pAudioManager) {
        volumeLevel = (Integer)pIntent.getExtras().get("android.media.EXTRA_VOLUME_STREAM_VALUE");
        volume = (int)(((float)volumeLevel/(float)pAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) *100) ;
    }

    public int getVolume() {
        return volume;
    }

    public int getVolumeLevel(AudioManager pAudioManager) {
        return (int) (((float)volume/(float)100)*(float)pAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
    }
}
