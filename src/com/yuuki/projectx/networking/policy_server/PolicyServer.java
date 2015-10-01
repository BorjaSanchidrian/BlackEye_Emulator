package com.yuuki.projectx.networking.policy_server;

import com.yuuki.projectx.networking.BasicServer;
import com.yuuki.projectx.utils.Console;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Borja
 * @date 27/08/2015
 * @package com.yuuki.projectx.networking.policy_server
 * @project ProjectX - Emulator
 */
public class PolicyServer extends BasicServer {
    private static final String SERVER_NAME = "[PolicyServer]";

    //used to handle connections
    private Socket socket;

    /**
     * Starts a socket server in the 'port' passed in the parameters
     * and sets a new thread with the selected 'serverName'
     *
     * @param port       Port used on the serverSocket
     * @throws IOException If the selected port is already used
     * @see BasicServer
     */
    public PolicyServer(int port) throws IOException {
        super(port, SERVER_NAME);
    }

    /**
     * Checks if the address which tries to connect has or not access to the server (banned)
     * and establish the connection
     */
    @Override
    public void run() {
        super.run();

        while(super.getServerSocket().isBound()) {
            try {
                //accepts the incoming connection
                socket = super.getServerSocket().accept();

                //checks if the address which tries to connect has or not access to the server
                if(super.checkAddress(socket.getInetAddress().getHostAddress())) {
                    //Pass the socket to the correspondent connection handler
                    new PolicyConnection(socket);
                } else {
                    Console.error(socket.getInetAddress().getHostAddress() + " was blocked for suspected DDoS " + SERVER_NAME);
                    socket.close();
                }
            } catch (IOException e) {
                Console.error("Something went wrong handling the connection", e.getMessage());
            }
        }
    }
}
