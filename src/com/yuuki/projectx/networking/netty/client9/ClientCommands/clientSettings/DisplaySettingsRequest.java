package com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings;


import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Borja
 * @date 30/06/2015
 * @package simulator.netty.ClientCommands.clientSettings
 * @project YuukiServer
 */
public class DisplaySettingsRequest extends ClientCommand {
    public static final int ID = 25227;

    public boolean displayWindowsBackground = false;
    public boolean dragWindowsAlways = false;
    public boolean hoverShips = false;
    public boolean displayNotifications = false;
    public boolean displayNotFreeCargoBoxes = false;
    public boolean displayBonusBoxes = false;
    public boolean displayDrones = false;
    public boolean displayResources = false;
    public boolean displayChat = false;
    public boolean showPremiumQuickslotBar = false;
    public boolean displayFreeCargoBoxes = false;
    public boolean allowAutoQuality = false;
    public boolean displayPlayerNames = false;
    public boolean showNotOwnedItems = false;
    public boolean preloadUserShips = false;
    public boolean displayHitpointBubbles = false;

    public DisplaySettingsRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            this.displayWindowsBackground = in.readBoolean();
            this.dragWindowsAlways = in.readBoolean();
            this.hoverShips = in.readBoolean();
            this.displayNotifications = in.readBoolean();
            this.displayNotFreeCargoBoxes = in.readBoolean();
            this.displayBonusBoxes = in.readBoolean();
            this.displayDrones = in.readBoolean();
            this.displayResources = in.readBoolean();
            this.displayChat = in.readBoolean();
            this.showPremiumQuickslotBar = in.readBoolean();
            this.displayFreeCargoBoxes = in.readBoolean();
            this.allowAutoQuality = in.readBoolean();
            this.displayPlayerNames = in.readBoolean();
            this.showNotOwnedItems = in.readBoolean();
            this.preloadUserShips = in.readBoolean();
            this.displayHitpointBubbles = in.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
