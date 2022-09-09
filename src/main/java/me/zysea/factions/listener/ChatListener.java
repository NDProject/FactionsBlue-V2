package me.zysea.factions.listener;

import me.zysea.factions.core.persist.CachePlayers;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.util.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final CachePlayers players;

    public ChatListener(CachePlayers cachePlayers) {
        this.players = cachePlayers;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event){
        FPlayer fPlayer = players.get(event.getPlayer().getUniqueId());
        String name = event.getPlayer().getDisplayName();

        event.setFormat(Chat.color(fPlayer.getRole().getName() + "&e " + name + " &e&l> &c") + event.getMessage());

    }
}
