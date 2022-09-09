package me.zysea.factions.listener;


import me.zysea.factions.core.persist.CachePlayers;
import me.zysea.factions.objects.Claim;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.Faction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class FactionTerritoryListener implements Listener {

    private CachePlayers cachePlayers;

    public FactionTerritoryListener(CachePlayers cachePlayers){
        this.cachePlayers = cachePlayers;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        FPlayer fPlayer = cachePlayers.get(event.getPlayer().getUniqueId());
        Claim current = new Claim(event.getBlock().getLocation());

        if(current.getFaction().isWilderness())
            return;

        Faction faction = fPlayer.getFaction();
        Faction target = current.getFaction();

        if(fPlayer.isBypass())
            return;

        if(faction != target) {
            event.setCancelled(true);
            return;
        }

        if(fPlayer.getRole().hasPermission("break"))
            return;

        event.setCancelled(true);
    }
}
