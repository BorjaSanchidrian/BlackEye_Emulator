package com.yuuki.projectx.networking.netty.client9.ServerCommands.classPackets;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Yuuki
 * @date 28/06/2015
 * @package simulator.netty.ServerCommands
 * @project S7KServer
 */
public class class_478 implements IServerCommand {
    public static int ID = 21343;


    public int x = 0;

    public int color = 0;
    public int var_2070 = 0;

    public int y = 0;

    public class_478(int x, int color, int var_2070, int y) {
        this.x = x;
        this.color = color;
        this.var_2070 = var_2070;
        this.y = y;
    }

    @Override
    public void write(DataOutputStream param1) {
        try {
            param1.writeInt(this.var_2070 << 3 | this.var_2070 >>> 29);

            param1.writeInt(this.color << 10 | this.color >>> 22);

            param1.writeInt(this.x >>> 8 | this.x << 24);

            param1.writeInt(this.y >>> 12 | this.y << 20);

            param1.writeShort(20706);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
