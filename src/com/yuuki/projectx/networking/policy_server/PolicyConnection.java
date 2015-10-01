package com.yuuki.projectx.networking.policy_server;

import com.yuuki.projectx.networking.ConnectionHandler;
import com.yuuki.projectx.utils.Console;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * @author Borja
 * @date 27/08/2015
 * @package com.yuuki.projectx.networking.policy_server
 * @project ProjectX - Emulator
 */
public class PolicyConnection extends ConnectionHandler {
    /**
     * This class will be used as template to handle all the incoming
     * connections for all the servers needed in this emulator
     *
     * @param socket Socket to handle the connection
     */
    public PolicyConnection(Socket socket) {
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
        if(packet.equals("<policy-file-request/>")) {
            sendPolicy();
        }
    }

    private void sendPolicy() {
        String policy = "<?xml version=\"1.0\"?>\r\n" +
                "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n" +
                "<cross-domain-policy>\r\n" +
                "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n" +
                "</cross-domain-policy>";
        sendPacket(policy);
        Console.out("Policy file sent");
    }
}
