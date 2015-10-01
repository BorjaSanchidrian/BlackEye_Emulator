package com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers;

import com.yuuki.projectx.game.managers.ClientConfigurationManager;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.GameplaySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import org.json.JSONException;

/**
 * @author Borja
 * @date 29/06/2015
 * @package simulator.netty.Handlers
 * @project YuukiServer
 */
public class GameplaySettingsHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private GameplaySettingsRequest gameplaySettingsRequest;

    public GameplaySettingsHandler(Client9Connection gameClientConnection, ClientCommand gameplaySettingsRequest) {
        this.gameClientConnection = gameClientConnection;
        this.gameplaySettingsRequest = (GameplaySettingsRequest) gameplaySettingsRequest;
    }

    @Override
    public void execute() {
        try {
            ClientConfigurationManager clientConfigurationSystem = ClientConfigurationManager.getInstance();
            clientConfigurationSystem.changeGameplaySettings(gameClientConnection.getPlayer().getSettings(), gameplaySettingsRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
