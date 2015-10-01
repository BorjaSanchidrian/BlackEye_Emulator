package com.yuuki.projectx.networking.netty.client9.ClientCommands;


import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Borja
 * @date 30/06/2015
 * @package simulator.netty.Handlers
 * @project YuukiServer
 */
public class ShipSelectionRequest extends ClientCommand {
    public static final int ID = 2806;

    public int clickedY = 0;
    public int clickedID = 0;
    public int userY = 0;
    public int userX = 0;
    public int clickedX = 0;

    public ShipSelectionRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            this.clickedY = in.readInt();
            this.clickedY = this.clickedY >>> 6 | this.clickedY << 26;
            in.readShort();
            this.clickedID = in.readInt();
            this.clickedID = this.clickedID << 14 | this.clickedID >>> 18;
            this.userY = in.readInt();
            this.userY = this.userY << 12 | this.userY >>> 20;
            this.userX = in.readInt();
            this.userX = this.userX << 2 | this.userX >>> 30;
            this.clickedX = in.readInt();
            this.clickedX = this.clickedX >>> 12 | this.clickedX << 20;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
