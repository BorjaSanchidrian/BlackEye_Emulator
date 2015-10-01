package com.yuuki.projectx.networking.netty.client9.Handlers.clientSettingsHandlers;

import com.yuuki.projectx.game.managers.ClientConfigurationManager;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings.AudioSettingsRequest;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import org.json.JSONException;

/**
 * @author Borja
 * @date 29/06/2015
 * @package simulator.netty.Handlers
 * @project YuukiServer
 */
public class AudioSettingsHandler implements ICommandHandler {
    private Client9Connection gameClientConnection;
    private AudioSettingsRequest audioSettings;

    public AudioSettingsHandler(Client9Connection gameClientConnection, ClientCommand audioSettings) {
        this.gameClientConnection = gameClientConnection;
        this.audioSettings = (AudioSettingsRequest) audioSettings;
    }

    @Override
    public void execute() {
        try {
            ClientConfigurationManager clientConfigurationSystem = ClientConfigurationManager.getInstance();
            clientConfigurationSystem.changeAudioSettings(gameClientConnection.getPlayer().getSettings(), audioSettings);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
