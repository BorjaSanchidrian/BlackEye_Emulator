package com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings;

import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Yuuki
 * @date 30/06/2015
 * @package simulator.netty.ClientCommands.clientSettings
 * @project YuukiServer
 */
public class GameplaySettingsRequest extends ClientCommand {
    public static final int ID = 13714;
    
    public boolean autoBuyBootyKeys = false;
    public boolean autoRefinement = false;
    public boolean autochangeAmmo = false;
    public boolean quickSlotStopAttack = false;
    public boolean autoStartEnabled = false;
    public boolean battlerayNotifications = false;
    public boolean doubleclickAttackEnabled = false;
    public boolean autoBoost = false;

    public GameplaySettingsRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            this.autoBuyBootyKeys = in.readBoolean();
            this.autoRefinement = in.readBoolean();
            this.autochangeAmmo = in.readBoolean();
            this.quickSlotStopAttack = in.readBoolean();
            this.autoStartEnabled = in.readBoolean();
            this.battlerayNotifications = in.readBoolean();
            this.doubleclickAttackEnabled = in.readBoolean();
            this.autoBoost = in.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
