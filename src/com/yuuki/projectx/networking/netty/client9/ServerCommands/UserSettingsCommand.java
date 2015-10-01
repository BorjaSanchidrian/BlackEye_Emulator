package com.yuuki.projectx.networking.netty.client9.ServerCommands;


import com.yuuki.projectx.networking.netty.client9.IServerCommand;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.settingsModules.*;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Yuuki
 * @date 22/05/2015
 * @package simulator.netty.ServerCommands
 * @project DOtest
 */
public class UserSettingsCommand
        implements IServerCommand {

    public static int ID = 8498;
    public AudioSettingsModule var_462;
    public QualitySettingsModule var_2933;
    public QuestsSettingsModule var_1456;
    public DisplaySettingsModule var_3026;
    public GameplaySettingsModule var_3290;
    public WindowSettingsModule var_2122;


    public UserSettingsCommand(AudioSettingsModule param1, QualitySettingsModule param2, QuestsSettingsModule param3,
                               DisplaySettingsModule param4, GameplaySettingsModule param5,
                               WindowSettingsModule param6) {
        this.var_462 = param1;
        this.var_2933 = param2;
        this.var_1456 = param3;
        this.var_3026 = param4;
        this.var_3290 = param5;
        this.var_2122 = param6;
    }

    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 0;
    }

    public void write(DataOutputStream param1) {
        try {
            param1.writeShort(ID);
            this.writeInternal(param1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInternal(DataOutputStream param1) {
        try {
            this.var_2122.write(param1);
            param1.writeShort(-10830);
            this.var_3026.write(param1);
            this.var_2933.write(param1);
            this.var_1456.write(param1);
            this.var_3290.write(param1);
            this.var_462.write(param1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
