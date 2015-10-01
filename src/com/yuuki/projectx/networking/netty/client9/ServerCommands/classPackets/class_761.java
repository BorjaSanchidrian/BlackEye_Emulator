package com.yuuki.projectx.networking.netty.client9.ServerCommands.classPackets;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 Created by Pedro on 14-03-2015.
 */
public class class_761
        implements IServerCommand {

    public static int ID = 32539;
    public Vector<class_436> updates;

    public class_761(Vector<class_436> param1) {
        this.updates = param1;
    }

    public void write(DataOutputStream param1) {
        try {
            param1.writeShort(ID);
            this.method_8(param1);
        } catch (IOException e) {
        }
    }

    protected void method_8(DataOutputStream param1) {
        try {
            param1.writeShort(15090);
            param1.writeInt(this.updates.size());
            for (class_436 c : this.updates) {
                c.write(param1);
            }
        } catch (IOException e) {
        }
    }
}
