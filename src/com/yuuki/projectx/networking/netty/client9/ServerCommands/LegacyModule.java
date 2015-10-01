package com.yuuki.projectx.networking.netty.client9.ServerCommands;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class LegacyModule
        implements IServerCommand {

    public static final int ID = 15145;

    public String message;

    public LegacyModule(String message) {
        this.message = message;
    }

    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 2;
    }

    public void write(DataOutputStream param1) {
        try {
            param1.writeShort(ID);
            this.writeInternal(param1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInternal(DataOutputStream param1) {
        try {
            param1.writeUTF(this.message);
            param1.writeShort(-28556);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}