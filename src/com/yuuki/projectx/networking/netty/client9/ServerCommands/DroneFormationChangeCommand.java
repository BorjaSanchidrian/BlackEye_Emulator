package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class DroneFormationChangeCommand
        implements IServerCommand {

    public DroneFormationChangeCommand(int playerID, int selectedFormation) {
        this.uid                 = playerID;
        this.selectedFormationId = selectedFormation;
    }

    public static int ID = 8735;

    public int selectedFormationId = 0;
    public int uid                 = 0;

    public void write(DataOutputStream playerID) {
        try {
            playerID.writeShort(ID);
            this.writeInternal(playerID);
        } catch (IOException e) {
        }
    }

    protected void writeInternal(DataOutputStream playerID) {
        try {
            playerID.writeShort(-29201);
            playerID.writeInt(this.selectedFormationId >>> 12 | this.selectedFormationId << 20);
            playerID.writeInt(this.uid << 11 | this.uid >>> 21);
            playerID.writeShort(29445);
        } catch (IOException e) {
        }
    }
}