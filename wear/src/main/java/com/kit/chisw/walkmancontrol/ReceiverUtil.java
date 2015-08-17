package com.kit.chisw.walkmancontrol;

import android.content.Intent;

import com.kit.chisw.walkmancontrol.model.TrackCompletedModel;
import com.kit.chisw.walkmancontrol.model.TrackPausedModel;
import com.kit.chisw.walkmancontrol.model.TrackPreparedModel;
import com.kit.chisw.walkmancontrol.model.TrackSkippedModel;
import com.kit.chisw.walkmancontrol.model.TrackStartedModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class ReceiverUtil {

    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o;
        try {
            o = new ObjectInputStream(b);
            return o.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
