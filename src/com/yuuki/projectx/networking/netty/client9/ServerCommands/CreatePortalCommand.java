package com.yuuki.projectx.networking.netty.client9.ServerCommands;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

public class CreatePortalCommand
        implements IServerCommand {

    public static int     ID         = 18277;
    public        int     designId   = 0;
    public        boolean var_89     = false;
    public        boolean showBubble = false;
    public        int     var_3576   = 0;
    public Vector<Integer> var_1648;
    public int x         = 0;
    public int factionId = 0;
    public int y         = 0;

    /**
     * new CreatePortalCommand(this.getMapEntityId(), this.getFactionIconId(), this.getGraphicsId(),
     this.getCurrentPositionX(), this.getCurrentPositionY(), this.isWorking(),
     this.isVisible(), new Vector<Integer>());
     * @param mapID
     * @param factionIconID
     * @param graphicsID
     * @param x
     * @param y
     * @param isActive
     * @param visible
     * @param param8
     */
    public CreatePortalCommand(int mapID, int factionIconID, int graphicsID, int x, int y, boolean isActive,
                               boolean visible, Vector<Integer> param8) {
        this.var_3576 = mapID;
        this.factionId = factionIconID;
        this.designId = graphicsID;
        this.x = x;
        this.y = y;
        this.var_89 = isActive;
        this.showBubble = visible;
        this.var_1648 = param8;
    }

    public void write(DataOutputStream mapID) {
        try {
            mapID.writeShort(ID);
            this.writeInternal(mapID);
        } catch (IOException e) {
        }
    }

    protected void writeInternal(DataOutputStream mapID) {
        try {
            mapID.writeInt(this.y << 4 | this.y >>> 28);
            mapID.writeBoolean(this.showBubble);
            mapID.writeInt(this.factionId << 6 | this.factionId >>> 26);
            mapID.writeBoolean(this.var_89);
            mapID.writeInt(this.designId >>> 4 | this.designId << 28);
            mapID.writeInt(this.var_3576 << 9 | this.var_3576 >>> 23);
            mapID.writeInt(this.var_1648.size());
            for (Integer i : this.var_1648) {
                mapID.writeInt(i >>> 12 | i << 20);
            }
            mapID.writeInt(this.x << 10 | this.x >>> 22);
        } catch (IOException e) {
        }
    }
}