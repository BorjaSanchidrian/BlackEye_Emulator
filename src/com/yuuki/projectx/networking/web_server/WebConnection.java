package com.yuuki.projectx.networking.web_server;

import com.yuuki.projectx.game.GameManager;
import com.yuuki.projectx.game.managers.PlayerManager;
import com.yuuki.projectx.game.objects.Player;
import com.yuuki.projectx.game.objects.PlayerEquipment;
import com.yuuki.projectx.game.objects.Ship;
import com.yuuki.projectx.mysql.QueryManager;
import com.yuuki.projectx.networking.ConnectionHandler;
import com.yuuki.projectx.networking.GameSession;
import com.yuuki.projectx.utils.Console;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.DataInputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * @author Borja
 * @date 27/08/2015
 * @package com.yuuki.projectx.networking.web_server
 * @project ProjectX - Emulator
 */
public class WebConnection extends ConnectionHandler {
    /**
     * This class will be used as template to handle all the incoming
     * connections for all the servers needed in this emulator
     *
     * @param socket Socket to handle the connection
     */
    public WebConnection(Socket socket) {
        super(socket);
        super.handlerName = this.getClass().getSimpleName();
        startBufferedReader();
    }

    @Override
    public void processPacket(DataInputStream packet) {
        // => Depreciated <=
    }

    @Override
    public void processPacket(String packet) {
        System.err.println("Packet from " + super.handlerName + ": " + packet);
        String[] p = packet.split("\\|");

        //Global vars
        int         playerID;
        int         hangarID;
        int         configID;
        GameSession gameSession;
        Player      player;

        switch(p[0]) {
            /**
             * Updates the player sessionID. That changes in each login
             */
            case "updateSessionID":
                playerID = Integer.parseInt(p[1]);
                String sessionID = p[2];
                gameSession = GameManager.getGameSession(playerID);

                player = getPlayer(playerID);

                if(player != null) {
                    player.setSessionID(sessionID);

                    //If the player is online updates its sessionID
                    if (gameSession != null) {
                        gameSession.getPlayer().setSessionID(sessionID);
                    }
                }
                break;

            /**
             * Updates player configs
             */
            case "updateConfigs":
                playerID = Integer.parseInt(p[1]);
                configID = Integer.parseInt(p[2]);
                hangarID = Integer.parseInt(p[3]);
                String configJSON = p[4];

                player = getPlayer(playerID);

                if(player != null) {
                    try {
                        /**
                         * Creates a new Equipment object and adds it to GameManager. QueryManager will
                         * search there first, if can't find anything will load it manually from the database
                         */

                        //if the hangar matches
                        if(player.getHangarID() == hangarID) {
                            PlayerEquipment playerEquipment = PlayerEquipment.parseEquipment(playerID, configID, configJSON, player.getCurrentShield());

                            GameManager.addPlayerEquipment(playerEquipment);

                            /**
                             * Checks if the player is online to update the configurations in-game
                             */
                            gameSession = GameManager.getGameSession(playerID);

                            if(gameSession != null) {
                                gameSession.getPlayer().setPlayerEquipment(configID, playerEquipment);
                                PlayerManager.getInstance().updatePlayer(gameSession);
                            }
                        }
                    } catch (JSONException | SQLException e) {
                        Console.error(e.getMessage());
                    }
                } else {
                    Console.error("Something went wrong retrieving the player account #" + playerID);
                }
                break;

            /**
             * Changes the ship model..
             */
            case "updateShipModel":
                playerID = Integer.parseInt(p[1]);
                hangarID = Integer.parseInt(p[2]);
                String lootID = p[3];

                player = getPlayer(playerID);

                if(player != null) {
                    /*
                     * Creates a new Equipment object and adds it to GameManager. QueryManager will
                     * search there first, if can't find anything will load it manually from the database
                     */

                    //if the hangar matches
                    if(player.getHangarID() == hangarID) {
                        Ship ship = GameManager.getShip(lootID);

                        if(ship != null) {
                            player.setShip(ship);

                            /**
                             * Checks if the player is online to update the ship in-game
                             */
                            gameSession = GameManager.getGameSession(playerID);

                            if(gameSession != null) {
                                //shouldn't be needed...
                                gameSession.getPlayer().setShip(ship);
                                PlayerManager.getInstance().updatePlayer(gameSession);
                            }

                        } else {
                            Console.error("Ship " + lootID + " doesn't exists on WebConnection.updateShipModel case");
                        }
                    }
                } else {
                    Console.error("Something went wrong retrieving the player account #" + playerID);
                }
                break;

            case "updateDroneConfig":
                //updateDroneConfig|1|96|[{"hangarID":1,"EQ":[{"configID":1,"default":["71","19"],"design":[]},{"configID":2,"default":[],"design":[]}]},{"hangarID":2,"EQ":[{"configID":1,"default":[],"design":[]},{"configID":2,"default":[],"design":[]}]}]
                playerID = Integer.parseInt(p[1]);
                int    droneID         = Integer.parseInt(p[2]);
                String droneConfigJSON = p[3];

                player = getPlayer(playerID);

                if(player != null) {
                    try {
                        JSONArray hangarsJSON = new JSONArray(droneConfigJSON);
                        /**
                         * Drone configurations
                         */
                        PlayerEquipment[] droneEquipment = null;

                        for (int i = 0; i < hangarsJSON.length(); i++) {
                            //select the configurations for the correct hangarID
                            if (hangarsJSON.getJSONObject(i).getInt("hangarID") == player.getHangarID()) {
                                //Gets the configurations json
                                String configurations = hangarsJSON.get(i).toString();
                                droneEquipment = PlayerEquipment.parseDroneEquipment(playerID, configurations);
                            }
                        }

                        //updates the equipment
                        player.updateDroneEquipment(droneID, droneEquipment);

                        /**
                         * Checks if the player is online to update the drones in-game
                         */
                        gameSession = GameManager.getGameSession(playerID);

                        if(gameSession != null) {
                            //shouldn't be needed...
                            gameSession.getPlayer().updateDroneEquipment(droneID, droneEquipment);
                            PlayerManager.getInstance().updatePlayer(gameSession);
                        }
                    } catch (JSONException | SQLException e) {
                        Console.error("Couldn't parse JSON String " + droneConfigJSON);
                        e.printStackTrace();
                    }

                } else {
                    Console.error("Something went wrong retrieving the player account #" + playerID);
                }

                break;
        }
    }

    /**
     * Gets the player account.
     * TODO check if the player is online or not, and block the equipment
     */
    public Player getPlayer(int playerID) {
        Player player;

        //check if the emulator has the player loaded
        player = GameManager.getPlayer(playerID);

        if(player == null) {
            //The emulator doesn't have the account, lets load it.
            try {
                player = QueryManager.loadPlayer(playerID);
            } catch (SQLException | JSONException e) {
                Console.error(e.getMessage());
            }
        }

        return player;
    }
}
