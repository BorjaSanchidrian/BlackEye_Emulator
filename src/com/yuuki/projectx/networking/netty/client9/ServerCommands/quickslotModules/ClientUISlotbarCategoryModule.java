package com.yuuki.projectx.networking.netty.client9.ServerCommands.quickslotModules;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Yuuki
 * @date 04/07/2015
 * @package simulator.netty.ServerCommands.quickslotModules
 * @project YuukiServer
 */
public class ClientUISlotbarCategoryModule implements IServerCommand {
    public static final int ID = 16643;

    public String categoryID = ""; //categoryID
    public Vector<ClientUISlotbarCategoryItemModule> categoryItems; //categoryItems

    public ClientUISlotbarCategoryModule(String categoryID, Vector<ClientUISlotbarCategoryItemModule> categoryItems) {
        this.categoryID = categoryID;
        this.categoryItems = categoryItems;
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
            param1.writeUTF(this.categoryID);
            param1.writeInt(this.categoryItems.size());
            for (ClientUISlotbarCategoryItemModule c : this.categoryItems) {
                c.write(param1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
