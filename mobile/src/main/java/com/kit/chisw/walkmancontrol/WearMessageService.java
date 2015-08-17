package com.kit.chisw.walkmancontrol;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by Kuzlo on 08.07.2015.
 */
public class WearMessageService extends WearableListenerService {
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        String s = new String(messageEvent.getData());
        switch (s){
            case "play":
                AudioManager fd =
                        (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                if(fd.isMusicActive()){
                    mediaKeyPress(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
                }else {
                    mediaKeyPress(KeyEvent.KEYCODE_MEDIA_PLAY);
                }

                break;

            case "prev":mediaKeyPress(KeyEvent.KEYCODE_MEDIA_PREVIOUS); break;
            case "next": mediaKeyPress(KeyEvent.KEYCODE_MEDIA_NEXT);break;
            case "pause":mediaKeyPress(KeyEvent.KEYCODE_MEDIA_PAUSE); break;
            case "play_pouse":mediaKeyPress(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE); break;
            case "get_volume":
//                AudioManager am2 =
//                        (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//
//                new WearConnectManager(this,"MEDIA",(int)(((float)am2.getStreamVolume(AudioManager.STREAM_MUSIC)/(float)am2.getStreamMaxVolume(AudioManager.STREAM_MUSIC))*100) +  "").send();
                break;
            default:
                int p = Integer.parseInt(s.split(" ")[1]);
                p = p>100?100:p;
                Log.d("OOOOO", "PPPP + " + p);
                AudioManager am =
                        (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                am.setStreamVolume( AudioManager.STREAM_MUSIC,
                        (int) (((float)am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)/(float)100)*(float)p),
                        AudioManager.FLAG_SHOW_UI);
                break;
        }

        super.onMessageReceived(messageEvent);

    }

    private void mediaKeyPress(int pKeyCode){
        Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
        synchronized (this) {
            i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN, pKeyCode));
            sendOrderedBroadcast(i, null);

            i.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP,pKeyCode));
            sendOrderedBroadcast(i, null);
        }
    }
}
