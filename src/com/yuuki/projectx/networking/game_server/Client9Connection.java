package com.yuuki.projectx.networking.game_server;

import com.yuuki.projectx.game.objects.Player;
import com.yuuki.projectx.networking.ConnectionHandler;
import com.yuuki.projectx.networking.netty.client9.ClientCommand;
import com.yuuki.projectx.networking.netty.client9.CommandLookup;
import com.yuuki.projectx.networking.netty.client9.HandlerLookup;
import com.yuuki.projectx.networking.netty.client9.ICommandHandler;
import com.yuuki.projectx.utils.Console;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * @author Borja
 * @date 27/08/2015
 * @package com.yuuki.projectx.networking.game_server
 * @project ProjectX - Emulator
 */
public class Client9Connection extends ConnectionHandler {

    //Player 'owner' of this connection
    private Player player;

    /**
     * Handler used for the Client 9
     *
     * @param socket Socket to handle the connection
     * @see ConnectionHandler
     */
    public Client9Connection(Socket socket) {
        super(socket);
        super.handlerName = this.getClass().getSimpleName();
        startDataInputStreamReader();
    }

    /**
     * This method search the Command in CommandLookup, if finds anything
     * will search and execute its correspondent handler.
     *
     * @param packet DataInputStream
     */
    @Override
    public void processPacket(DataInputStream packet) {
        ClientCommand clientCommand = CommandLookup.getCommand(packet);
        if(clientCommand != null) {
            Console.out("Packet received with ID=" + clientCommand.getID());
            ICommandHandler commandHandler = HandlerLookup.getHandler(this, clientCommand);

            if(commandHandler != null) {
                Console.out("Executing handler for command with ID=" + clientCommand.getID());
                commandHandler.execute();
            }
        }
    }

    @Override
    public void processPacket(String packet) {
        // => Depreciated <=
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
