package com.yuuki.projectx.networking.netty.client9;

import java.io.DataInputStream;

/**
 * @author Borja
 * @date 11/09/2015
 * @package com.yuuki.projectx.networking.netty
 * @project ProjectX - Emulator
 */
public abstract class ClientCommand {
    protected DataInputStream in;
    private short id;

    public ClientCommand(DataInputStream in, int id) {
        this.in = in;
        this.id = (short) id;
    }

    public int getID() {
        return this.id;
    }

    public void read() {
        readInternal();
    }

    /**
     * Will be overwritten.
     */
    public abstract void readInternal();
}