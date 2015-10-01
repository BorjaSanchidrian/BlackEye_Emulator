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
public class WindowSettingsModule
        implements IServerCommand {

    public static int     ID              = 319;
    public        boolean mHideAllWindows = false;
    public        int     mScale          = 0;
    public        String  mBarState       = "";

    public WindowSettingsModule(int mScale, String mBarState, boolean mHideAllWindows) {
        this.mScale = mScale;
        this.mBarState = mBarState;
        this.mHideAllWindows = mHideAllWindows;
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
            param1.writeShort(-8579);
            param1.writeBoolean(this.mHideAllWindows);
            param1.writeUTF(this.mBarState);
            param1.writeInt(this.mScale << 9 | this.mScale >>> 23);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
