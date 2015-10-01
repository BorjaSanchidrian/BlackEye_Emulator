package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class HealCommand
        implements IServerCommand {

    public static short HITPOINTS = 0;
    public static short SHIELD    = 1;

    public static int ID = 27966;

    public short healType;
    public int   currentHitpoints;
    public int   healAmount;
    public int   healerId;
    public int   healedId;

    public HealCommand(short healType, int healerID, int healedID, int currentHitpoints, int healAmount) {
        this.healType         = healType;
        this.healerId         = healerID;
        this.healedId         = healedID;
        this.currentHitpoints = currentHitpoints;
        this.healAmount       = healAmount;
    }

    public void write(DataOutputStream healType) {
        try {
            healType.writeShort(ID);
            this.writeInternal(healType);
        } catch (IOException e) {
        }
    }

    protected void writeInternal(DataOutputStream healType) {
        try {
            healType.writeShort(this.healType);
            healType.writeShort(-20306);
            healType.writeInt(this.currentHitpoints >>> 4 | this.currentHitpoints << 28);
            healType.writeInt(this.healAmount << 2 | this.healAmount >>> 30);
            healType.writeInt(this.healerId >>> 14 | this.healerId << 18);
            healType.writeShort(-10690);
            healType.writeInt(this.healedId >>> 12 | this.healedId << 20);
        } catch (IOException e) {
        }
    }
}
