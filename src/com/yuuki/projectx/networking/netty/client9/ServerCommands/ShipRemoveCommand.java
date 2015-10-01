package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Borja
 * @date 28/06/2015
 * @package simulator.netty.ServerCommands
 * @project S7KServer
 */
public class ShipRemoveCommand implements IServerCommand {
    public static int ID = 20034;

    public int id;

    public ShipRemoveCommand(int id) {
        this.id = id;
    }

    @Override
    public void write(DataOutputStream out) {
        try {
            out.writeShort(ID);
            this.writeInternal(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInternal(DataOutputStream param1) {
        try {
            param1.writeInt(this.id << 13 | this.id >>> 19);
            param1.writeShort(4726);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
