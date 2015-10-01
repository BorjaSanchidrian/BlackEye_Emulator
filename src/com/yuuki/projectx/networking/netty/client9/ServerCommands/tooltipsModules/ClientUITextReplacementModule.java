package com.yuuki.projectx.networking.netty.client9.ServerCommands.tooltipsModules;

import com.yuuki.projectx.networking.netty.client9.IServerCommand;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientUITextReplacementModule
        implements IServerCommand {

    public static int ID = 1020;

    public String mReplacement = "";

    public String mWildcard = "";

    public ClientUITooltipTextFormatModule mTooltipTextFormat;

    public ClientUITextReplacementModule(String pWildCard, ClientUITooltipTextFormatModule pTooltipTextFormat,
                                         String pReplacement) {
        this.mWildcard = pWildCard;
        this.mReplacement = pReplacement;
        this.mTooltipTextFormat = pTooltipTextFormat;
    }

    public int getID() {
        return ID;
    }

    public int method_1005() {
        return 4;
    }

    public void write(DataOutputStream out) {
        try {
            out.writeShort(ID);
            this.writeInternal(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeInternal(DataOutputStream out) {
        try {
            this.mTooltipTextFormat.write(out);
            out.writeUTF(this.mWildcard);
            out.writeUTF(this.mReplacement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}