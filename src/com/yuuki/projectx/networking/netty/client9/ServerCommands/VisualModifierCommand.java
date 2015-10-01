package com.yuuki.projectx.networking.netty.client9.ServerCommands;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 */
public class VisualModifierCommand
        implements IServerCommand {

    public static int ID = 21462;

    public static short SOME_RADAR_SHIT    = 34;
    public static short DIMINISHER     = 12;
    public static short HEAL_ICON    = 36;
    public static short CHANGE_SHIP    = 18;
    public static short SPECTRUM    = 10;
    public static short SPIN_EMP     = 38;
    public static short DIMINISHER_2    = 11;
    public static short const_1736    = 1;
    public static short NEON_GREEN     = 22;
    public static short const_1099    = 7;
    public static short CHANGE_SHIP_EFFECT     = 15;
    public static short STRANGE_1    = 39;
    public static short const_55      = 35;
    public static short const_804     = 24;
    public static short TA_CAMERA    = 45;
    public static short const_768     = 33;
    public static short VANISH    = 19;
    public static short const_1621    = 21;
    public static short BUBBLESHIELD    = 9;
    public static short INACTIVE      = 8;
    public static short LIGHTING    = 0;
    public static short GET_FIRE     = 3; //citadel??
    public static short STRANGE_2    = 25;
    public static short STRANGE_3    = 41;
    public static short RED_SWORD     = 16;
    public static short const_523     = 5;
    public static short CONTROL_LOST    = 20; //pierdes el control de la nave
    public static short SHIP_ASSEMBLE    = 29;
    public static short SINGULARITY   = 13;
    public static short DAMAGE_BOOSTER    = 43; //pone un simbolo similar encima de la nave
    public static short SOLACE     = 42;
    public static short const_2653    = 31;
    public static short NEON_BLUE     = 17;
    public static short SINGULARITY_2    = 14;
    public static short TARGET_MARKER_BLUE    = 47;
    public static short TARGET_MARKER    = 46;
    public static short CITADEL    = 4;
    public static short SHIELD_ICON     = 37;
    public static short const_1184    = 44;
    public static short const_1060    = 40;
    public static short INVINCIBILITY = 26;
    public static short const_2574    = 32;
    public static short NEON_3    = 23;
    public static short INVINCIBILITY_STATIC      = 27;
    public static short ROBOT_ARMS     = 30;
    public static short FORTIFY    = 2;
    public static short DRAW_FIRE    = 6;
    public static short TIMER    = 28;

    public int attribute = 0;

    public int playerID = 0;

    public int count = 0;

    public String var_1770 = "";

    public short var_1913 = 0;

    public boolean activated = false;

    public VisualModifierCommand(int playerID, short param2, int pAttribute, String param4, int pCount,
                                 boolean pActivated) {

        this.playerID = playerID;
        this.var_1913 = param2;
        this.attribute = pAttribute;
        this.var_1770 = param4;
        this.count = pCount;
        this.activated = pActivated;
    }


    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 15;
    }

    public void write(DataOutputStream out) {
        try {
            out.writeShort(ID);
            this.writeInternal(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeInternal(DataOutputStream out) {
        try {
            out.writeUTF(this.var_1770);
            out.writeShort(10961);
            out.writeInt(this.count << 15 | this.count >>> 17);
            out.writeShort(14097);
            out.writeShort(this.var_1913);
            out.writeInt(this.attribute >>> 13 | this.attribute << 19);
            out.writeInt(this.playerID >>> 2 | this.playerID << 30);
            out.writeBoolean(this.activated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}