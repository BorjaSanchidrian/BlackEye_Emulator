package com.yuuki.projectx.networking.netty.client9.ServerCommands;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Used to move a movable object
 * @author Borja
 * @date 28/06/2015
 * @package simulator.netty.ServerCommands
 * @project S7KServer
 */
public class MovementCommand implements IServerCommand {
    public static int ID = 27383;

    public int y = 0;
    public int x = 0;
    public int duration = 0;
    public int objectID = 0;

    public MovementCommand(int objectID, int x, int y, int duration) {
        this.y = y;
        this.x = x;
        this.duration = duration;
        this.objectID = objectID;
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
            param1.writeShort(16229);
            param1.writeInt(this.y >>> 14 | this.y << 18);
            param1.writeInt(this.x << 4 | this.x >>> 28);
            param1.writeInt(this.duration >>> 15 | this.duration << 17);
            param1.writeInt(this.objectID >>> 12 | this.objectID << 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
