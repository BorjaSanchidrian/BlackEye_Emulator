package com.yuuki.projectx.networking.netty.client9.Handlers;

import com.yuuki.projectx.game.managers.MovementManager;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.MovementRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;

import java.awt.*;

/**
 * @author Yuuki
 * @date 26/06/2015
 * @package simulator.netty.Handlers
 * @project S7KServer
 */
public class MovementRequestHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private MovementRequest movementRequest;

    public MovementRequestHandler(Client9Connection gameClientConnection, ClientCommand movementRequest) {
        this.movementRequest = (MovementRequest) movementRequest;
        this.gameClientConnection = gameClientConnection;
    }

    @Override
    public void execute() {
        //TODO Im not sure if you can speed hack with this, if you detect something strange check the old positions too
        MovementManager.getInstance().move(gameClientConnection.getPlayer(), new Point(movementRequest.newX, movementRequest.newY));
    }
}
