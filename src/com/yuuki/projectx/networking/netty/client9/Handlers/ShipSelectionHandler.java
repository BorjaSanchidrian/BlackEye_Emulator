package com.yuuki.projectx.networking.netty.client9.Handlers;


import com.yuuki.projectx.game.GameManager;
import com.yuuki.projectx.game.managers.PlayerManager;
import com.yuuki.projectx.networking.GameSession;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.ShipSelectionRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import com.yuuki.projectx.utils.Console;

import java.awt.*;

/**
 * @author Borja
 * @date 30/06/2015
 * @package simulator.netty.Handlers
 * @project YuukiServer
 */
public class ShipSelectionHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private ShipSelectionRequest shipSelectionRequest;

    public ShipSelectionHandler(Client9Connection gameClientConnection, ClientCommand shipSelectionRequest) {
        this.gameClientConnection = gameClientConnection;
        this.shipSelectionRequest = (ShipSelectionRequest) shipSelectionRequest;
    }

    @Override
    public void execute() {
        GameSession gameSession = GameManager.getGameSession(gameClientConnection.getPlayer().getEntityID());

        if(gameSession != null) {
            Point clickedPosition = new Point(shipSelectionRequest.clickedX, shipSelectionRequest.clickedY);

            //Check if from the position given by the packet is possible for the player to click someone
            if(gameSession.getPlayer().getPosition().distance(clickedPosition) <= gameSession.getPlayer().getRenderRange()) {
                PlayerManager.getInstance().selectCharacter(gameSession, shipSelectionRequest.clickedID);
            } else {
                Console.error("Seems like someone is trying to hack playerID #" + gameSession.getPlayer().getEntityID());
            }
        }
    }
}
