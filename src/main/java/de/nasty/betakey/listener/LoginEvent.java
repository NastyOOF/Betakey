package de.nasty.betakey.listener;

import de.nasty.betakey.database.Beta;
import de.nasty.betakey.utils.UUIDFetcher;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvent implements Listener {

    @EventHandler
    public static void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        String playername = player.getPlayer().getName();
        String playerID = UUIDFetcher.getUUID(playername).toString();

        if(!Beta.isExistingAccount(playerID)) {
            Beta.createAccount(playerID);
            e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "§7Du hast keinen Beta Zugriff! \n §7Du kannst einen Schlüssel unter \n  \n §chttps://deinserver.net/redeem \n  \n §7einlösen!");
        } else {
            if(Beta.getWhitelisted(playerID) == 0) {
                e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "§7Du hast keinen Beta Zugriff! \n §7Du kannst einen Schlüssel unter \n  \n §chttps://deinserver.net/redeem \n  \n §7einlösen!");
            }
        }
    }
}
