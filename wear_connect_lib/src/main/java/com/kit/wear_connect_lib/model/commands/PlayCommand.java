package com.kit.wear_connect_lib.model.commands;

import java.io.Serializable;

/**
 * Created by Kuzlo on 20.08.2015.
 */
public class PlayCommand implements Serializable {
    public static final int PLAY = 0x1;
    public static final int PAUSE = 0x2;
    public static final int PREVIOUS = 0x3;
    public static final int NEXT = 0x4;
    public static final int STOP = 0x5;


    private int type;

    public PlayCommand(int pType) {
        type = pType;
    }

    public int getType() {
        return type;
    }
}
