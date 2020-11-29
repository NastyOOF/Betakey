package de.nasty.betakey.commands;

import de.nasty.betakey.Main;
import de.nasty.betakey.database.Beta;
import de.nasty.betakey.utils.Keys;
import de.nasty.betakey.utils.UUIDFetcher;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BetaCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player)commandSender;
        String playername = player.getPlayer().getName();
        String playerID = UUIDFetcher.getUUID(playername).toString();

        if(player.hasPermission("beta.admin")) {
            if(args.length == 0) {
                player.sendMessage(Main.getMain().getPrefix() + "§7Benutze: /beta <add/remove> <player>");
            }
            if(args.length > 0) {
                if(args[0].equalsIgnoreCase("add")) {
                    if(args.length == 2) {
                        String target = args[1];
                        String targetID = UUIDFetcher.getUUID(target).toString();
                        if(Beta.isExistingAccount(targetID)) {
                            if(Beta.getWhitelisted(targetID) == 0) {
                                Beta.setWhitelisted(targetID, 1);
                                player.sendMessage(Main.getMain().getPrefix() + "§c" + target + " §7hat jetzt Beta Zugriff!");
                            } else {
                                player.sendMessage(Main.getMain().getPrefix() + "§c" + target + " §7hat bereits Beta Zugriff!");
                            }
                        } else {
                            Beta.createAccount(targetID);
                            Beta.setWhitelisted(targetID, 1);
                            player.sendMessage(Main.getMain().getPrefix() + "§c" + target + " §7hat jetzt Beta Zugriff!");
                        }
                    } else {
                        player.sendMessage(Main.getMain().getPrefix() + "§7Benutze: /beta <add/remove> <player>");
                    }
                } else if(args[0].equalsIgnoreCase("remove")) {
                    if (args.length == 2) {
                        String target = args[1];
                        String targetID = UUIDFetcher.getUUID(target).toString();
                        if (Beta.isExistingAccount(targetID)) {
                            if (Beta.getWhitelisted(targetID) == 0) {
                                player.sendMessage(Main.getMain().getPrefix() + "§c" + target + " §7hat keinen Beta Zugriff!");
                            } else {
                                Beta.setWhitelisted(targetID, 0);
                                player.sendMessage(Main.getMain().getPrefix() + "§c" + target + " §7hat jetzt keinen Beta Zugriff mehr!");
                            }
                        } else {
                            player.sendMessage(Main.getMain().getPrefix() + "§c" + target + " §7existiert nicht in der Datenbank!");
                        }
                    } else {
                        player.sendMessage(Main.getMain().getPrefix() + "§7Benutze: /beta <add/remove> <player>");
                    }
                } else if(args[0].equalsIgnoreCase("generate")) {
                    String randomKey = Keys.generate();
                    player.sendMessage(Main.getMain().getPrefix() + "§7Dein Key: §c" + randomKey);
                    Beta.insertKey(randomKey);
                } else {
                    player.sendMessage(Main.getMain().getPrefix() + "§7Benutze: /beta <add/remove> <player>");
                }
            }
        } else {
            player.sendMessage(Main.getMain().getPrefix() + "§cDazu hast du keine Rechte!");
        }
        return false;
    }
}
