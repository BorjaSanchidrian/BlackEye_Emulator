package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.quickslotModules.ClientUISlotbarCategoryModule;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.quickslotModules.ClientUISlotbarModule;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Yuuki
 * @date 04/07/2015
 * @package simulator.netty.ServerCommands
 * @project YuukiServer
 */
public class ClientUISlotBarCommand implements IServerCommand {
    public static final int ID = 8220;

    public Vector<ClientUISlotbarCategoryModule> categories;

    public Vector<ClientUISlotbarModule> slotBars;

    public String var_2836 = "";

    public ClientUISlotBarCommand(Vector<ClientUISlotbarCategoryModule> categories, Vector<ClientUISlotbarModule> slotBars, String var_2836) {
        this.categories = categories;
        this.slotBars = slotBars;
        this.var_2836 = var_2836;
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
            param1.writeInt(this.categories.size());
            for(ClientUISlotbarCategoryModule  c : this.categories) {
               c.write(param1);
            }

            param1.writeInt(this.slotBars.size());
            for(ClientUISlotbarModule c : this.slotBars) {
                c.write(param1);
            }
            param1.writeUTF(this.var_2836);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
