package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class AttributeShieldUpdateCommand
        implements IServerCommand {

    public AttributeShieldUpdateCommand(int pShieldNow, int pShieldMax) {
        this.shieldNow = pShieldNow;
        this.shieldMax = pShieldMax;
    }

    public static int ID = 1343;

    public int shieldNow = 0;
    public int shieldMax = 0;

    public void write(DataOutputStream param1) {
        try {
            param1.writeShort(ID);
            this.writeInternal(param1);
        } catch (IOException e) {
        }
    }

    protected void writeInternal(DataOutputStream param1) {
        try {
            param1.writeShort(-18399);
            param1.writeInt(this.shieldMax >>> 9 | this.shieldMax << 23);
            param1.writeInt(this.shieldNow << 14 | this.shieldNow >>> 18);
        } catch (IOException e) {
        }
    }
}