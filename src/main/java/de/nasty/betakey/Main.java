/*
 * Class created by Nasty / Ron S.
 * 29.11.20, 05:48
 */

package de.nasty.betakey;

import de.nasty.betakey.commands.BetaCMD;
import de.nasty.betakey.database.MySQL;
import de.nasty.betakey.listener.LoginEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.CommunicationException;
import java.sql.SQLException;

public final class Main extends JavaPlugin {
    private String prefix = "§7[§cBeta§7] ";
    static Main main;

    @Override
    public void onEnable() {
        main = this;
        try {
            MySQL.connect();
            MySQL.update("CREATE TABLE IF NOT EXISTS beta (uuid VARCHAR(255), whitelisted INT(16))");
            MySQL.update("CREATE TABLE IF NOT EXISTS betacodes (codes VARCHAR(255), used INT(16))");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§4§lEs konnte keine Verbindung zu MYSQL hergestellt werden!");
        }
        events();
        commands();
        startMessage();
    }

    private void events() {
        Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);
    }

    private void commands() {
        getCommand("beta").setExecutor(new BetaCMD());
    }

    private void startMessage() {
        System.out.println("                                                                                       ");
        System.out.println("                                                                                       ");
        System.out.println(" ____       _        _                _             _   _           _         ");
        System.out.println("|  _ \\     | |      | |              | |           | \\ | |         | |        ");
        System.out.println("| |_) | ___| |_ __ _| | _____ _   _  | |__  _   _  |  \\| | __ _ ___| |_ _   _ ");
        System.out.println("|  _ < / _ \\ __/ _` | |/ / _ \\ | | | | '_ \\| | | | | . ` |/ _` / __| __| | | |");
        System.out.println("| |_) |  __/ || (_| |   <  __/ |_| | | |_) | |_| | | |\\  | (_| \\__ \\ |_| |_| |");
        System.out.println("|____/ \\___|\\__\\__,_|_|\\_\\___|\\__, | |_.__/ \\__, | |_| \\_|\\__,_|___/\\__|\\__, |");
        System.out.println("                               __/ |         __/ |                       __/ |");
        System.out.println("                              |___/         |___/                       |___/ ");
        System.out.println("                                                                                       ");
        Bukkit.getConsoleSender().sendMessage("                              §a§lLOADED SUCCESSFULLY");
        System.out.println("                                                                                       ");
        System.out.println("                                                                                       ");
    }

    public String getPrefix() {
        return prefix;
    }

    public static Main getMain() {
        return main;
    }
}
