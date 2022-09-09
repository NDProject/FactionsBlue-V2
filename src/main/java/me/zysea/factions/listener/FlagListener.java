package me.zysea.factions.listener;

import me.zysea.factions.core.flags.Flags;
import me.zysea.factions.core.flags.interactive.DenyInteractFlag;
import me.zysea.factions.core.persist.CachePlayers;
import me.zysea.factions.objects.Claim;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.Faction;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Iterator;

public class FlagListener implements Listener {

    private CachePlayers players;

    public FlagListener(CachePlayers cachePlayers) {
        this.players = cachePlayers;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if(event.getClickedBlock() == null)
            return;

        Faction faction = new Claim(event.getClickedBlock().getLocation()).getFaction();
        if (!faction.hasFlag("deny_interact"))
            return;

        FPlayer fPlayer = players.get(event.getPlayer().getUniqueId());

        if(fPlayer.isBypass())
            return;

        DenyInteractFlag flag = (DenyInteractFlag)faction.getInteractiveFlag("deny_interact");

        if(flag.getIds().contains(event.getClickedBlock().getType()))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        FPlayer fPlayer = players.get(event.getPlayer().getUniqueId());
        Faction faction = new Claim(event.getBlock().getLocation()).getFaction();

        if (!faction.hasFlag(Flags.DENY_BREAK) || fPlayer.isBypass())
            return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        FPlayer fPlayer = players.get(event.getPlayer().getUniqueId());
        Faction faction = new Claim(event.getBlock().getLocation()).getFaction();

        if (faction.hasFlag(Flags.DENY_BUILD) && fPlayer.isBypass())
            return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFill(PlayerBucketFillEvent event) {
        FPlayer fPlayer = players.get(event.getPlayer().getUniqueId());
        Faction faction = new Claim(event.getBlockClicked().getLocation()).getFaction();

        if (faction.hasFlag(Flags.DENY_BREAK) && fPlayer.isBypass())
            return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEmpty(PlayerBucketEmptyEvent event) {
        FPlayer fPlayer = players.get(event.getPlayer().getUniqueId());
        Faction faction = new Claim(event.getBlockClicked().getLocation()).getFaction();

        if (faction.hasFlag(Flags.DENY_BUILD) && fPlayer.isBypass())
            return;

        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onExplode(EntityExplodeEvent event) {
        Claim claim = new Claim(event.getLocation());

        Flags flag = event.getEntity() instanceof TNTPrimed ? Flags.DENY_TNT_EXPLOSION : Flags.DENY_MOB_EXPLOSION;

        if (claim.getFaction().hasFlag(flag)) {
            event.setCancelled(true);
            return;
        }

        Iterator<Block> iterator = event.blockList().iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            Claim damagedClaim = new Claim(block.getLocation());
            if (!claim.equals(damagedClaim)) {
                claim = damagedClaim;
            }

            if (claim.getFaction().hasFlag(flag))
                iterator.remove();
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Flags flag = event.getCause() == EntityDamageEvent.DamageCause.FALL ? Flags.NO_FALL_DAMAGE : Flags.NO_DAMAGE;
        Claim claim = new Claim(event.getEntity().getLocation());

        if (claim.getFaction().hasFlag(flag))
            event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPvP(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Entity damager = event.getDamager() instanceof Projectile ? (Entity)((Projectile)event.getDamager()).getShooter() : event.getDamager();
        if(!(damager instanceof Player))
            return;

        Claim claim = new Claim(event.getEntity().getLocation());

        if (claim.getFaction().hasFlag(Flags.DENY_PVP)) {
            event.setCancelled(true);
            return;
        }

        claim = new Claim(damager.getLocation());

        if (!claim.getFaction().hasFlag(Flags.DENY_PVP))
            return;

        event.setCancelled(true);
    }


    @EventHandler(ignoreCancelled = true)
    public void onHungerChange(FoodLevelChangeEvent event) {
        if (((Player) event.getEntity()).getFoodLevel() > event.getFoodLevel()
                && new Claim(event.getEntity().getLocation()).getFaction().hasFlag(Flags.NO_HUNGER))
            event.setCancelled(true);
    }

}
