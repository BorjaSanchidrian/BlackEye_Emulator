package com.yuuki.projectx.networking.netty.client9;

import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.*;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.AudioSettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.DisplaySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.GameplaySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.QualitySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.Handlers.*;
import com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers.AudioSettingsHandler;
import com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers.DisplaySettingsHandler;
import com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers.GameplaySettingsHandler;
import com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers.QualitySettingsHandler;
import com.yuuki.projectx.utils.Console;

import java.util.TreeMap;

/**
 * @author Yuuki
 * @date 22/05/2015
 * @package net.game_server
 * @project DOtest
 */
public class HandlerLookup {
    private static TreeMap<Integer, Class> commandHandlers;

    /**
     * This method create a new HashMap and adds all the handlers to the commands that the client can send us.
     *
     * commandsLookup.put(Key (Integer), Value (Class));
     * Key => Command to handle
     * Value => 'Action to do when we get that command' CommandHandler
     */
    public static void initLookup() {
        commandHandlers = new TreeMap<>();
        commandHandlers.put(VersionRequest.ID, VersionRequestHandler.class);
        commandHandlers.put(MovementRequest.ID, MovementRequestHandler.class);
        commandHandlers.put(LegacyModuleRequest.ID, LegacyModuleRequestHandler.class);
        commandHandlers.put(AudioSettingsRequest.ID, AudioSettingsHandler.class);
        commandHandlers.put(DisplaySettingsRequest.ID, DisplaySettingsHandler.class);
        commandHandlers.put(QualitySettingsRequest.ID, QualitySettingsHandler.class);
        commandHandlers.put(GameplaySettingsRequest.ID, GameplaySettingsHandler.class);
        commandHandlers.put(UserKeyBindingsUpdate.ID, UserKeyBindingsUpdateHandler.class);
        commandHandlers.put(ShipSelectionRequest.ID, ShipSelectionHandler.class);
    }

    /**
     * The same as getCommand in CommandLookup
     * @param gameClientConnection Passed to the handler, used to send back the right command.
     * @param clientCommand Command that we want to handle
     * @return The right handler if found. Null if not
     */
    @SuppressWarnings("unchecked") //Because the compiler gives a wrong optimization
    public static ICommandHandler getHandler(Client9Connection gameClientConnection, ClientCommand clientCommand) {
        try {
            Class commandHandler = commandHandlers.get(clientCommand.getID());

            if(commandHandler != null) {
                return (ICommandHandler) commandHandler.getConstructor(Client9Connection.class, ClientCommand.class)
                        .newInstance(gameClientConnection, clientCommand);
            } else {
                Console.error("Handler not found for command with ID=" + clientCommand.getID());
            }
        } catch(Exception e) {
            Console.error("Something went wrong in CommandHandler", e.getMessage());
        }
        return null;
    }
}
