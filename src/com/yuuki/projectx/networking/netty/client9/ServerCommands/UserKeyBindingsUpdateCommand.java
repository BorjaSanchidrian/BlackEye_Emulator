package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

public class UserKeyBindingsUpdateCommand
        implements IServerCommand {

    public static int     ID     = 8739;
    public        boolean remove = false;
    public Vector<UserKeyBindingsModuleCommand> var_2472;

    public UserKeyBindingsUpdateCommand(Vector<UserKeyBindingsModuleCommand> param1, boolean pRemove) {
        this.var_2472 = param1;
        this.remove = pRemove;
    }

    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 5;
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
            out.writeInt(this.var_2472.size());
            for (UserKeyBindingsModuleCommand c : this.var_2472) {
                c.write(out);
            }
            out.writeBoolean(this.remove);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}