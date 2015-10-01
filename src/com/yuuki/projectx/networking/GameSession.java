package com.yuuki.projectx.networking;

import com.yuuki.projectx.game.GameManager;
import com.yuuki.projectx.game.objects.Player;
import com.yuuki.projectx.game.objects.Spacemap;
import com.yuuki.projectx.networking.game_server.Client9Connection;
import com.yuuki.projectx.utils.Console;

/**
 * This class will store all the sockets and handlers of each player
 * @author Yuuki
 * @date 12/09/2015 | 17:33
 * @package com.yuuki.projectx.networking
 */
public class GameSession {
    //Handlers
    private Client9Connection client9Connection;

    //GameSession "owner"
    private Player player;

    public GameSession(Player player) {
        this.player = player;
        Console.out("Created new GameSession #" + player.getEntityID() + " -> " + player.getName());
    }

    /**
     * Finish the online GameSession for the player
     */
    public void closeSession() {
        if(client9Connection != null) {
            client9Connection.closeConnection();
            Console.out("GameSession finished or refreshed for " + player.getName() + " #" + player.getEntityID());
        }

        //Removes the online session
        GameManager.removeGameSession(player.getEntityID());

        //Removes the player from the spacemap
        Spacemap spacemap = GameManager.getSpacemap(player.getMapID());
        if (spacemap != null) {
            spacemap.removeEntity(player.getEntityID());
        }
    }

    /***********************
     * GETTERS AND SETTERS *
     ***********************/
    public Client9Connection getClient9Connection() {
        return client9Connection;
    }

    public void setClient9Connection(Client9Connection client9Connection) {
        this.client9Connection = client9Connection;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
