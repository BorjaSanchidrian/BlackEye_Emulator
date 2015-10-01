package com.yuuki.projectx.networking.netty.client9;

import com.yuuki.projectx.networking.netty.client9.ClientCommands.*;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.*;
import com.yuuki.projectx.utils.Console;

import java.io.DataInputStream;
import java.util.TreeMap;

/**
 * @author Borja
 * @date 21/05/2015
 * @package net.game_server
 * @project DOtest
 */
public class CommandLookup {
    private static TreeMap<Integer, Class> commandsLookup;

    /**
     * This method create a new HashMap and adds all the commands that the client can send us.
     *
     * commandsLookup.put(Key (Integer), Value (Class));
     * commandsLookup.put(VersionRequest.ID, VersionRequest.class);
     */
    public static void initLookup() {
        /* All the client commands will be here */
        commandsLookup = new TreeMap<>();
        commandsLookup.put(VersionRequest.ID, VersionRequest.class);
        commandsLookup.put(MovementRequest.ID, MovementRequest.class);
        commandsLookup.put(LegacyModuleRequest.ID, LegacyModuleRequest.class);
        commandsLookup.put(AudioSettingsRequest.ID, AudioSettingsRequest.class);
        commandsLookup.put(DisplaySettingsRequest.ID, DisplaySettingsRequest.class);
        commandsLookup.put(QualitySettingsRequest.ID, QualitySettingsRequest.class);
        commandsLookup.put(GameplaySettingsRequest.ID, GameplaySettingsRequest.class);
        commandsLookup.put(WindowSettingsRequest.ID, WindowSettingsRequest.class);
        commandsLookup.put(UserKeyBindingsUpdate.ID, UserKeyBindingsUpdate.class);
        commandsLookup.put(ShipSelectionRequest.ID, ShipSelectionRequest.class);
    }

    /**
     * This method search into commandsLookup and if it founds anything will instance a new object ClientCommand
     * As all the Commands extends ClientCommand if we get:
     *
     * commandID = 666; => VersionRequest ID
     *
     * commandClass will be netty.ClientCommands.VersionRequest.class So we can cast it to ClientCommand because VersionRequest
     * extends ClientCommand. Easy
     *
     * @param in DataInputStream (from GameClientConnection)
     * @return ClientCommand
     */
    @SuppressWarnings("unchecked") //Because the compiler gives a wrong optimization
    public static ClientCommand getCommand(DataInputStream in) {
        try {
            int commandID = in.readShort();
            Class commandClass = commandsLookup.get(commandID);

            if(commandClass != null) {
                ClientCommand clientCommand = (ClientCommand) commandClass.getConstructor(DataInputStream.class).newInstance(in);
                clientCommand.read();
                return clientCommand;
            } else {
                Console.error("Command with ID=" + commandID + " not found on CommandLookup");
            }
        } catch(Exception e) {
            Console.error("Couldn't read from DataInputStream in CommandLookup", e.getMessage());
        }
        return null;
    }
}
