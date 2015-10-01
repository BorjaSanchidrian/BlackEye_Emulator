package com.yuuki.projectx.networking.netty.client9.ServerCommands.quickslotModules;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.ClientUITooltipsCommand;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Yuuki
 * @date 04/07/2015
 * @package simulator.netty.ServerCommands.quickslotModules
 * @project YuukiServer
 */
public class ClientUISlotbarCategoryItemStatusModule implements IServerCommand {
    public static final int ID = 24696;

    public static final short ORANGE = 6;
    public static final short DEFAULT = 0;
    public static final short GREEN = 2;
    public static final short CYAN = 5;
    public static final short YELLOW = 3;
    public static final short BLUE = 4;
    public static final short RED = 1;

    public boolean blocked = false;
    public boolean buyable = false;
    public short counterStyle = 0;
    public boolean selected = false;
    public ClientUITooltipsCommand toolTipItemBar;
    public String id = "";
    public boolean available = false;
    public ClientUITooltipsCommand toolTipSlotBar;
    public double counterValue = 0;
    public boolean visible = false;
    public String iconLootId = "";
    public double maxCounterValue = 0;
    public boolean activatable = false;

    public ClientUISlotbarCategoryItemStatusModule(boolean blocked, boolean buyable, short counterStyle, boolean selected,
                                                   ClientUITooltipsCommand toolTipItemBar, String id, boolean available, ClientUITooltipsCommand toolTipSlotBar,
                                                   double counterValue, boolean visible, String iconLootId, double maxCounterValue, boolean activatable) {
        this.blocked = blocked;
        this.buyable = buyable;
        this.counterStyle = counterStyle;
        this.selected = selected;
        this.toolTipItemBar = toolTipItemBar;
        this.id = id;
        this.available = available;
        this.toolTipSlotBar = toolTipSlotBar;
        this.counterValue = counterValue;
        this.visible = visible;
        this.iconLootId = iconLootId;
        this.maxCounterValue = maxCounterValue;
        this.activatable = activatable;
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
            param1.writeBoolean(this.blocked);
            param1.writeBoolean(this.buyable);
            param1.writeShort(this.counterStyle);
            param1.writeBoolean(this.selected);
            this.toolTipItemBar.write(param1);
            param1.writeUTF(this.id);
            param1.writeBoolean(this.available);
            this.toolTipSlotBar.write(param1);
            param1.writeDouble(this.counterValue);
            param1.writeBoolean(this.visible);
            param1.writeUTF(this.iconLootId);
            param1.writeDouble(this.maxCounterValue);
            param1.writeBoolean(this.activatable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
