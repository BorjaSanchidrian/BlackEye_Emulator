package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

public class UserKeyBindingsModuleCommand
        implements IServerCommand {

    public static int ID = 27620;
    public Vector<Integer> var_2616;
    public short charCode = 0;
    public short var_1545 = 0;
    public int   var_2477 = 0;

    public static final short JUMP                   =  0;
    public static final short CHANGE_CONFIG          =  1;
    public static final short ACTIVATE_LASER         =  2;
    public static final short LAUNCH_ROCKET          =  3;
    public static final short PET_ACTIVATE           =  4;
    public static final short PET_GUARD_MODE         =  5;
    public static final short LOGOUT                 =  6;
    public static final short QUICKSLOT              =  7;
    public static final short QUICKSLOT_PREMIUM      =  8;
    public static final short TOGGLE_WINDOWS         =  9;
    public static final short PERFORMANCE_MONITORING = 10;
    public static final short ZOOM_IN                = 11;
    public static final short ZOOM_OUT               = 12;
    public static final short PET_REPAIR_SHIP        = 13;
    public static final short const_1529             = 15;
    public static final short const_866              = 14;

    public UserKeyBindingsModuleCommand(short actionType, Vector<Integer> keyCodes, int parameter, short pCharCode) {
        this.var_1545 = actionType;
        this.var_2616 = keyCodes;
        this.var_2477 = parameter;
        this.charCode = pCharCode;
    }

    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 10;
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
            out.writeInt(this.var_2477 >>> 11 | this.var_2477 << 21);
            out.writeShort(24954);
            out.writeInt(this.var_2616.size());
            for (Integer i : this.var_2616) {
                out.writeInt(i >>> 9 | i << 23);
            }
            out.writeShort(this.var_1545);
            out.writeShort(8530);
            out.writeShort(this.charCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}