package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdInfo extends SubCommand {

    public CmdInfo() {
        super("info", "get info", "i");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());
        sender.sendMessage("Your Faction: " + fPlayer.getFaction().getName());
    }
}
