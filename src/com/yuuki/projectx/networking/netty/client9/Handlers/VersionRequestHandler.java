package com.yuuki.projectx.networking.netty.client9.Handlers;

import com.yuuki.projectx.game.managers.LoginManager;
import com.yuuki.projectx.networking.GameSession;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.VersionRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import com.yuuki.projectx.utils.Console;
import org.json.JSONException;

import java.sql.SQLException;

/**
 * @author Yuuki
 * @date 26/06/2015
 * @package simulator.netty.Handlers
 * @project S7KServer
 */
public class VersionRequestHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private VersionRequest versionRequest;

    public VersionRequestHandler(Client9Connection gameClientConnection, ClientCommand versionRequest) {
        this.versionRequest = (VersionRequest) versionRequest;
        this.gameClientConnection = gameClientConnection;
    }

    @Override
    public void execute() {
        try {
            GameSession gameSession = LoginManager.getInstance().checkLogin(versionRequest.playerID, versionRequest.sessionID);

            if(gameSession != null) {
                //Logged in (3O.o)3
                //adds the game handler to the gameSession
                gameSession.setClient9Connection(gameClientConnection);
                gameClientConnection.setPlayer(gameSession.getPlayer());

                LoginManager.getInstance().executeLogin(gameSession);
            } else {
                Console.error("Couldn't connect player " + versionRequest.playerID);
            }

        } catch (SQLException | JSONException e) {
            Console.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
