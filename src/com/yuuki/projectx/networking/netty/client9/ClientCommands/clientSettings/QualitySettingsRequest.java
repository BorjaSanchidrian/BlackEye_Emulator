package com.yuuki.projectx.networking.netty.client9.ClientCommands.clientSettings;

import com.yuuki.projectx.networking.netty.client9.ClientCommand;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Yuuki
 * @date 30/06/2015
 * @package simulator.netty.ClientCommands.clientSettings
 * @project YuukiServer
 */
public class QualitySettingsRequest extends ClientCommand {
    public static final int ID = 32602;

    public int qualityBackground = 0;
    public int qualityAttack = 0;
    public int qualityPoizone = 0;
    public boolean qualityCustomized = false;
    public int qualityEngine = 0;
    public int qualityPresetting = 0;
    public int qualityEffect = 0;
    public int qualityCollectable = 0;
    public int qualityShip = 0;
    public int qualityExplosion = 0;

    public QualitySettingsRequest(DataInputStream in) {
        super(in, ID);
    }

    @Override
    public void readInternal() {
        try {
            this.qualityBackground  = in.readShort  ();
            this.qualityAttack      = in.readShort  ();
            this.qualityPoizone     = in.readShort  ();
            this.qualityCustomized  = in.readBoolean();
            this.qualityEngine      = in.readShort  ();
            this.qualityPresetting  = in.readShort  ();
            this.qualityEffect      = in.readShort  ();
            this.qualityCollectable = in.readShort  ();
            this.qualityShip        = in.readShort  ();
            this.qualityExplosion   = in.readShort  ();
            in.readShort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
