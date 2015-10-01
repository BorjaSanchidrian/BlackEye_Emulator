package com.yuuki.projectx.networking.netty.client9.ServerCommands.testPackets;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class Test2
        implements IServerCommand {

    public static final int ID = 6945;

    public int name_72 = 0;

    public int var_1454 = 0;

    public int hp = 0;

    public int var_492 = 0;

    public int shield = 0;

    public int var_2536 = 0;

    public Test2(int param1, int param2, int param3, int param4, int param5, int param6)
    {
        this.hp = param1;
        this.var_492 = param2;
        this.shield = param5;
        this.name_72 = param6;
        this.var_1454 = param3;
        this.var_2536 = param4;
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
            param1.writeShort(16549);
            param1.writeInt(this.name_72 << 14 | this.name_72 >>> 18);
            param1.writeInt(this.var_1454 >>> 9 | this.var_1454 << 23);
            param1.writeInt(this.hp << 16 | this.hp >>> 16);
            param1.writeInt(this.var_492 << 3 | this.var_492 >>> 29);
            param1.writeInt(this.shield >>> 7 | this.shield << 25);
            param1.writeInt(this.var_2536 >>> 1 | this.var_2536 << 31);
        } catch (IOException e) {
        }
    }
}
