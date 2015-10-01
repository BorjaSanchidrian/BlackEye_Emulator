package com.yuuki.projectx.networking.netty.client9.ServerCommands.settingsModules;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Yuuki
 * @date 22/05/2015
 * @package simulator.netty.ServerCommands
 * @project DOtest
 */
public class AudioSettingsModule
        implements IServerCommand {

    public static int     ID               = 6686;
    public        boolean mNotSet          = false;
    public        boolean mPlayCombatMusic = false;
    public        int     mVoice           = 0;
    public        int     mSound           = 0;
    public        int     mMusic           = 0;

    public AudioSettingsModule(boolean pNotSet, int pSound, int pMusic, int pVoice, boolean pPlayCombatMusic) {
        this.mNotSet = pNotSet;
        this.mSound = pSound;
        this.mMusic = pMusic;
        this.mVoice = pVoice;
        this.mPlayCombatMusic = pPlayCombatMusic;
    }

    public int getID() {
        return ID;
    }

    public void write(DataOutputStream param1) {
        try {
            param1.writeShort(ID);
            this.writeInternal(param1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeInternal(DataOutputStream param1) {
        try {
            param1.writeInt(this.mSound << 16 | this.mSound >>> 16);
            param1.writeBoolean(this.mPlayCombatMusic);
            param1.writeInt(this.mVoice >>> 7 | this.mVoice << 25);
            param1.writeBoolean(this.mNotSet);
            param1.writeInt(this.mMusic >>> 8 | this.mMusic << 24);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}