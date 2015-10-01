package com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings;


import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Is sent when the user changes the audio settings on the client
 * @author Borja
 * @date 29/06/2015
 * @package simulator.netty.ClientCommands.clientSettings
 * @project YuukiServer
 */
public class AudioSettingsRequest extends ClientCommand {
    public static final int ID = 3057;

    public int voice = 0;
    public boolean playCombatMusic = false;
    public int music = 0;
    public int sound = 0;


    public AudioSettingsRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            this.voice = in.readInt();
            this.voice = this.voice >>> 8 | this.voice << 24;
            this.playCombatMusic = in.readBoolean();
            this.music = in.readInt();
            this.music = this.music << 7 | this.music >>> 25;
            this.sound = in.readInt();
            this.sound = this.sound >>> 6 | this.sound << 26;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
