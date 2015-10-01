package com.yuuki.projectx.networking.netty.client9.ServerCommands.quickslotModules;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Yuuki
 * @date 04/07/2015
 * @package simulator.netty.ServerCommands.quickslotModules
 * @project YuukiServer
 */
public class ClientUISlotbarCategoryItemModule implements IServerCommand {
    public static final int ID = 16680;

    //actionStyle
    public static final int TIMER = 3;
    public static final int TOOGLE = 1;
    public static final int SELECTION = 2;
    public static final int ONE_SHOT = 0;
    //counter const
    public static final int DOTS = 3;
    public static final int NUMBER = 1;
    public static final int BAR = 2;
    public static final int NONE = 0;

    public int counterType = 0;
    public CooldownTypeModule var_800;
    public ClientUISlotbarCategoryItemStatusModule status;
    public int var_560 = 0;
    public ClientUISlotBarCategoryItemTimerModule timer;
    public int actionStyle = 0;

    public ClientUISlotbarCategoryItemModule(int counterType, CooldownTypeModule var_800, ClientUISlotbarCategoryItemStatusModule status,
                                             int var_560, ClientUISlotBarCategoryItemTimerModule timer, int actionStyle) {
        this.counterType = counterType;
        this.var_800 = var_800;
        this.status = status;
        this.var_560 = var_560;
        this.timer = timer;
        this.actionStyle = actionStyle;
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

    private void writeInternal(DataOutputStream param1) {
        try {
            param1.writeShort(this.counterType);
            this.var_800.write(param1);
            this.status.write(param1);
            param1.writeInt(this.var_560 >>> 13 | this.var_560 << 19);
            this.timer.write(param1);
            param1.writeShort(this.actionStyle);
            param1.writeShort(-19107);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
