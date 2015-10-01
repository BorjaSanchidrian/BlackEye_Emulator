package com.yuuki.projectx.networking.netty.client9.ClientCommands;


import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Borja
 * @date 28/06/2015
 * @package simulator.netty.ClientCommands
 * @project S7KServer
 */
public class MovementRequest extends ClientCommand {
    public static final int ID = 18484;

    public int oldY = 0;
    public int newX = 0;
    public int oldX = 0;
    public int newY = 0;

    public MovementRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            this.oldY = in.readInt();
            this.oldY = this.oldY << 5 | this.oldY >>> 27;
            this.newX = in.readInt();
            this.newX = this.newX >>> 9 | this.newX << 23;
            this.oldX = in.readInt();
            this.oldX = this.oldX >>> 4 | this.oldX << 28;
            this.newY = in.readInt();
            this.newY = this.newY >>> 14 | this.newY << 18;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
