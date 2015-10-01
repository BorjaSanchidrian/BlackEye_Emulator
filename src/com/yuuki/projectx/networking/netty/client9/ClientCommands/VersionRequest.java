package com.yuuki.projectx.networking.netty.client9.ClientCommands;


import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.utils.Console;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Gets the playerID and sessionID from the player
 * @author Yuuki
 * @date 26/06/2015
 * @package simulator.netty.ClientCommands
 * @project ProjectX - Emulator
 */
public class VersionRequest extends ClientCommand {
    public static final int ID = 666;

    public int playerID;
    public String sessionID;

    public VersionRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            playerID = in.readInt();
            sessionID = in.readUTF();
        } catch (IOException e) {
            Console.error(e.getMessage());
        }
    }
}
