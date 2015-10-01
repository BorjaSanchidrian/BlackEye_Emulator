package com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings;

import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * TODO take a look again to this command :/
 * @author Yuuki
 * @date 30/06/2015
 * @package simulator.netty.ClientCommands.clientSettings
 * @project YuukiServer
 */
public class WindowSettingsRequest extends ClientCommand {
    public static final int ID = 29742;

    public String itemId = "";

    public WindowSettingsRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            this.itemId = in.readUTF();
            in.readShort();
            in.readShort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
