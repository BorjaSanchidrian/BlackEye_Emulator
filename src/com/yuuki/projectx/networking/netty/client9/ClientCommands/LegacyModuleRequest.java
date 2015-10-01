package com.yuuki.projectx.networking.netty.client9.ClientCommands;


import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

public class LegacyModuleRequest extends ClientCommand {

    public static final int ID = 15145;

    public String message;

    /**
     Constructor
     */
    public LegacyModuleRequest(DataInputStream in) {
        super(in, ID);
    }

    /**
     Description: Reads command
     */
    public void readInternal() {
        try {
            message = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
