package com.yuuki.projectx.networking.netty.client9.ServerCommands.testPackets;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class Test1
        implements IServerCommand {

    public static final short const_872 = 7;

    public static final short const_106 = 9;

    public static final short const_1712 = 8;

    public static final short EP = 0;

    public static final short const_870 = 10;

    public static final short DAMAGE = 2;

    public static final short const_461 = 5;

    public static final short const_69 = 6;

    public static final short REPAIR = 4;

    public static final short HONOUR = 1;

    public static final short SHIELD = 3;

    public static final int ID = 20371;

    public short var_1313;

    public Test1(short var_1313) {
        this.var_1313 = var_1313;
    }

    public void write(DataOutputStream healType) {
        try {
            healType.writeShort(ID);
            this.writeInternal(healType);
        } catch (IOException e) {
        }
    }

    protected void writeInternal(DataOutputStream param1) {
        try {
            param1.writeShort(this.var_1313);
            param1.writeShort(7272);
        } catch (IOException e) {
        }
    }
}
