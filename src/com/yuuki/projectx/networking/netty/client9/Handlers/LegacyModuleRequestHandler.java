package com.yuuki.projectx.networking.netty.client9.Handlers;

import com.yuuki.projectx.game.GameManager;
import com.yuuki.projectx.game.managers.PlayerManager;
import com.yuuki.projectx.networking.GameSession;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.LegacyModuleRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import com.yuuki.projectx.networking.netty.client9.ServerCommands.LegacyModule;
import com.yuuki.projectx.utils.Console;

/**
 * @author Yuuki
 * @date 26/06/2015
 * @package simulator.netty.Handlers
 * @project S7KServer
 */
public class LegacyModuleRequestHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private LegacyModuleRequest legacyModuleRequest;

    public LegacyModuleRequestHandler(Client9Connection gameClientConnection, ClientCommand legacyModuleRequest) {
        this.legacyModuleRequest = (LegacyModuleRequest) legacyModuleRequest;
        this.gameClientConnection = gameClientConnection;
    }

    @Override
    public void execute() {
        String[] packet = legacyModuleRequest.message.split("\\|");

        int         playerID;
        String      sessionID;
        GameSession gameSession;

        switch(packet[0]) {
            case "S":
                switch(packet[1]) {
                    case "CFG":
                        //S|CFG|2|1|x98JSK2xrnGX6eCaZ1kbyHldMHecBrVL => example change config packet
                        playerID  = Integer.parseInt(packet[3]);
                        sessionID = packet[4];
                        int newConfigID = Integer.parseInt(packet[2]);

                        gameSession = GameManager.getGameSession(playerID);

                        if(gameSession != null) {
                            //SessionID check to avoid feel like hackers
                            if(gameSession.getPlayer().getSessionID().equals(sessionID)) {
                                //Checks if the user can change the configuration
                                if(PlayerManager.getInstance().canChangeConfig(gameSession.getPlayer())) {
                                    PlayerManager.getInstance().changeConfig(gameSession, newConfigID);
                                } else {
                                    //send "you must wait" message
                                    gameClientConnection.sendCommand(new LegacyModule("0|A|STM|config_change_failed_time"));
                                }
                            } else {
                                Console.error("Wrong sessionID for player #" + playerID + " on " + getClass().getSimpleName() + ".S|CFG");
                            }
                        }
                        break;
                }
                break;
        }
    }
}
