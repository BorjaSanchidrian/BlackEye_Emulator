package com.yuuki.projectx.networking.netty.client9.ServerCommands;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Borja
 * @date 01/07/2015
 * @package simulator.netty.ServerCommands
 * @project YuukiServer
 */
public class ShipSelectionCommand implements IServerCommand {
    public static final int ID = 23689;

    public int maxShield = 0;
    public int maxNanoHull = 0;
    public int health = 0;
    public int maxHealth = 0;
    public int selectedID = 0;
    public boolean pilotsheetShieldSkill = false;
    public int var_2852 = 0;
    public int shield = 0;
    public int nanoHull = 0;

    public ShipSelectionCommand(int maxShield, int maxNanoHull, int health, int maxHealth, int selectedID, boolean pilotsheetShieldSkill, int var_2852, int shield, int nanoHull) {
        this.maxShield = maxShield;
        this.maxNanoHull = maxNanoHull;
        this.health = health;
        this.maxHealth = maxHealth;
        this.selectedID = selectedID;
        this.pilotsheetShieldSkill = pilotsheetShieldSkill;
        this.var_2852 = var_2852;
        this.shield = shield;
        this.nanoHull = nanoHull;
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

    private void writeInternal(DataOutputStream out) {
        try {
            out.writeInt(this.maxShield >>> 7 | this.maxShield << 25);
            out.writeInt(this.maxNanoHull << 5 | this.maxNanoHull >>> 27);
            out.writeShort(-16109);
            out.writeInt(this.health >>> 13 | this.health << 19);
            out.writeInt(this.maxHealth >>> 12 | this.maxHealth << 20);
            out.writeInt(this.selectedID >>> 2 | this.selectedID << 30);
            out.writeBoolean(this.pilotsheetShieldSkill);
            out.writeInt(this.var_2852 << 4 | this.var_2852 >>> 28);
            out.writeInt(this.shield >>> 15 | this.shield << 17);
            out.writeInt(this.nanoHull >>> 4 | this.nanoHull << 28);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
