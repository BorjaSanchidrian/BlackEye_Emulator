package com.yuuki.projectx.networking.netty.client9.Handlers;

import com.yuuki.projectx.game.managers.ClientConfigurationManager;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.UserKeyBindingsUpdate;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import org.json.JSONException;

/**
 * @author Yuuki
 * @date 26/06/2015
 * @package simulator.netty.Handlers
 * @project S7KServer
 */
public class UserKeyBindingsUpdateHandler implements ICommandHandler {
    private Client9Connection     gameClientConnection;
    private UserKeyBindingsUpdate userKeyBindingsUpdate;

    public UserKeyBindingsUpdateHandler(Client9Connection gameClientConnection, ClientCommand userKeyBindingsUpdate) {
        this.userKeyBindingsUpdate = (UserKeyBindingsUpdate) userKeyBindingsUpdate;
        this.gameClientConnection = gameClientConnection;
    }

    @Override
    public void execute() {
        try {
            ClientConfigurationManager clientConfigurationSystem = ClientConfigurationManager.getInstance();
            clientConfigurationSystem.changeUserKeyBindings(gameClientConnection.getPlayer(), userKeyBindingsUpdate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
