package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.defaults.factions.PlayerFaction;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.util.Chat;
import me.zysea.factions.util.Messages;
import me.zysea.factions.util.Perms;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCreate extends SubCommand {

    public CmdCreate() {
        super("create", "Create a faction", "cr", "make");
        setPermission(Perms.CMD_CREATE);
        setPlayersOnly(true);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());

        // Check if player has faction.
        if(fPlayer.hasFaction()){
            Chat.send(player, Messages.inFaction);
            return;
        }

        // If player has no faction then proceed to create faction.
        PlayerFaction playerFaction = new PlayerFaction(args[0]);
        playerFaction.getMembers().add(player.getUniqueId(), playerFaction.getMembers().getRole("Leader"));
        fPlayer.setFaction(playerFaction);
        FactionsBlue.getInstance().getCacheFactions().put(playerFaction.getId(), playerFaction);
        Chat.send(player, Messages.createFaction);
    }
}
