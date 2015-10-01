package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class AttributeHitpointUpdateCommand
        implements IServerCommand {

    public AttributeHitpointUpdateCommand(int currentHealth, int maxHealth, int currentNanohull, int maxNanohull) {
        this.name_60 = currentHealth;
        this.name_25 = maxHealth;
        this.var_1454 = currentNanohull;
        this.var_2536 = maxNanohull;
    }

    public static int ID = 28524;

    public int name_60  = 0;
    public int name_25  = 0;
    public int var_2536 = 0;
    public int var_1454 = 0;

    public void write(DataOutputStream currentHealth) {
        try {
            currentHealth.writeShort(ID);
            this.writeInternal(currentHealth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeInternal(DataOutputStream currentHealth) {
        try {
            currentHealth.writeInt(this.name_60 << 6 | this.name_60 >>> 26);
            currentHealth.writeInt(this.name_25 >>> 2 | this.name_25 << 30);
            currentHealth.writeInt(this.var_2536 >>> 14 | this.var_2536 << 18);
            currentHealth.writeInt(this.var_1454 >>> 8 | this.var_1454 << 24);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
