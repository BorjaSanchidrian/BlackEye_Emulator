package com.yuuki.projectx.main;

import com.yuuki.projectx.networking.ServerManager;
import com.yuuki.projectx.utils.Console;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Borja
 * @date 26/08/2015
 * @package com.yuuki.projectx.main
 * @project ProjectX - Emulator
 */
public class Launcher {
    //This is everything... XD
    public static void main(String[] args) {
        sendHeader();
        try {
            ServerManager.getInstance().init();
        } catch (IOException | SQLException | JSONException e) {
            //Something went wrong
            Console.error(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Prints the console header
     */
    private static void sendHeader() {
        System.out.println("Project X | Emulator v0.2");
        System.out.println("Developed by Yuuki");
        System.out.println(Console.LINE_MINUS);
    }
}
