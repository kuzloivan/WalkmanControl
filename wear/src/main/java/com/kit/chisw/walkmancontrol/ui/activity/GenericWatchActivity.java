package com.kit.chisw.walkmancontrol.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.wearable.view.WatchViewStub;

import com.kit.chisw.walkmancontrol.R;

/**
 * Created by Kuzlo on 14.07.2015.
 */
public abstract class GenericWatchActivity extends Activity implements WatchViewStub.OnLayoutInflatedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());
        WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        if(stub==null){
            throw new RuntimeException("Root layout ");
        }
        stub.setOnLayoutInflatedListener(this);
    }

    public abstract int setContentLayout();


}
