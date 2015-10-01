package com.yuuki.projectx.networking.game_server;

import com.yuuki.projectx.networking.BasicServer;
import com.yuuki.projectx.utils.Console;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Borja
 * @date 26/08/2015
 * @package com.yuuki.projectx.networking.game_server
 * @project ProjectX - Emulator
 */
public class GameServer extends BasicServer {
    private static final String SERVER_NAME = "[GameServer]";

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
    public GameServer(int port) throws IOException {
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
                    /**
                     * I'm thinking about making the server 'multi-client'
                     * maybe the best option is make a switch here depending of the port
                     * and execute the appropriate handler for the connection.
                     */
                    switch(super.getServerSocket().getLocalPort()) {
                        /*
                         * Client 9 connection
                         */
                        case 8080:
                            new Client9Connection(socket);
                            break;

                        case 8081:
                            //Another one?
                            break;

                        default:
                            Console.error(socket.getInetAddress().getHostAddress() + " tried to connect to a " +
                                    "wrong server on port " + socket.getLocalPort());
                            break;
                    }
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
