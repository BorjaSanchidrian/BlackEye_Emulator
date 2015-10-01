package com.yuuki.projectx.networking.netty.client9.ServerCommands;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClanRelationModule
        implements IServerCommand {

    public static short NONE = 0;

    public static short ALLIANCE = 1;
    public static short PNA  = 2;
    public static short WAR  = 3;

    public static int ID = 2061;

    public short type = 0;

    public ClanRelationModule(short pType) {
        this.type = pType;
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

    protected void writeInternal(DataOutputStream out) {
        try {
            out.writeShort(this.type);
            out.writeShort(15770);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}