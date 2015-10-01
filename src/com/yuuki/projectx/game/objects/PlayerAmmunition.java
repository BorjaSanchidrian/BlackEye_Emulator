package com.yuuki.projectx.game.objects;

import org.json.JSONArray;

/**
 * @author Yuuki
 * @date 14/09/2015 | 18:20
 * @package com.yuuki.projectx.game.objects
 */
public class PlayerAmmunition {
    //Basic vars
    private int playerID;

    //JSONArrays
    private JSONArray laserAmmunition;

    public PlayerAmmunition(int playerID, JSONArray laserAmmunition) {
        this.playerID = playerID;
        this.laserAmmunition = laserAmmunition;
    }

    /**
     * GETTERS
     */
    public int getPlayerID() {
        return playerID;
    }

    public JSONArray getLaserAmmunition() {
        return laserAmmunition;
    }
}
