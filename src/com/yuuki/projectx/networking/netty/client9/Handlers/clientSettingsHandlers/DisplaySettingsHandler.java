package com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers;


import com.yuuki.projectx.game.managers.ClientConfigurationManager;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.DisplaySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import org.json.JSONException;

/**
 * @author Yuuki
 * @date 30/06/2015
 * @package simulator.netty.Handlers.clientSettingsHandlers
 * @project YuukiServer
 */
public class DisplaySettingsHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private DisplaySettingsRequest displaySettingsRequest;

    public DisplaySettingsHandler(Client9Connection gameClientConnection, ClientCommand displaySettingsRequest) {
        this.gameClientConnection = gameClientConnection;
        this.displaySettingsRequest = (DisplaySettingsRequest) displaySettingsRequest;
    }

    @Override
    public void execute() {
        try {
            ClientConfigurationManager clientConfigurationSystem = ClientConfigurationManager.getInstance();
            clientConfigurationSystem.changeDisplaySettings(gameClientConnection.getPlayer().getSettings(), displaySettingsRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
