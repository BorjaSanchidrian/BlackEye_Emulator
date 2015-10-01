package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.tooltipsModules.ClientUITooltipModule;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

public class ClientUITooltipsCommand
        implements IServerCommand {

    public static int ID = 4659;

    public Vector<ClientUITooltipModule> mTooltips;

    public ClientUITooltipsCommand(Vector<ClientUITooltipModule> pTooltips) {
        this.mTooltips = pTooltips;
    }

    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 4;
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
            out.writeInt(this.mTooltips.size());
            for (ClientUITooltipModule c : this.mTooltips) {
                c.write(out);
            }
            out.writeShort(9452);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}