package com.yuuki.projectx.networking.netty.client9.ServerCommands.classPackets;



import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class class_365
        implements IServerCommand {

    public static short ALLY = 2;

    public static short DEFAULT = 0;

    public static short const_2330 = 1;

    public static int ID = 15604;

    public short var_3436 = 0;

    public class_365(short param1) {
        this.var_3436 = param1;
    }

    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 0;
    }

    public void write(DataOutputStream out) {
        try {
            out.writeShort(ID);
            this.writeInternal(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInternal(DataOutputStream out) {
        try {
            out.writeShort(this.var_3436);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}