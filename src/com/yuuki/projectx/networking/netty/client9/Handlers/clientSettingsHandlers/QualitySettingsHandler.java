package com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers;


import com.yuuki.projectx.game.managers.ClientConfigurationManager;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.QualitySettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import org.json.JSONException;

/**
 * @author Yuuki
 * @date 30/06/2015
 * @package simulator.netty.Handlers.clientSettingsHandlers
 * @project YuukiServer
 */
public class QualitySettingsHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private QualitySettingsRequest qualitySettingsRequest;

    public QualitySettingsHandler(Client9Connection gameClientConnection, ClientCommand qualitySettingsRequest) {
        this.gameClientConnection = gameClientConnection;
        this.qualitySettingsRequest = (QualitySettingsRequest) qualitySettingsRequest;
    }

    @Override
    public void execute() {
        try {
            ClientConfigurationManager clientConfigurationSystem = ClientConfigurationManager.getInstance();
            clientConfigurationSystem.changeQualitySettings(gameClientConnection.getPlayer().getSettings(), qualitySettingsRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
