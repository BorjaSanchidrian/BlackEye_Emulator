package com.yuuki.projectx.networking.netty.client9.ServerCommands.quickslotModules;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientUISlotbarItemModule
        implements IServerCommand {

    public static int    ID       = 15331;
    public        String var_1474 = "";
    public        int    slotId   = 0;

    public ClientUISlotbarItemModule(String param2, int pSlotId) {
        this.slotId = pSlotId;
        this.var_1474 = param2;
    }


    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 6;
    }


    public void write(DataOutputStream out) {
        try {
            out.writeShort(ID);
            this.writeInternal(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeInternal(DataOutputStream out) {
        try {
            out.writeUTF(this.var_1474);
            out.writeInt(this.slotId << 16 | this.slotId >>> 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}