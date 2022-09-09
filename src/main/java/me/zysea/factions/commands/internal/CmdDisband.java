package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.defaults.factions.PlayerFaction;
import me.zysea.factions.util.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdDisband extends SubCommand{

    public CmdDisband() {
        super("disband", "disband faction", "d");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());
        if(!fPlayer.hasFaction()){
            Chat.send(sender, "&cYou're not currently within a faction.");
            return;
        }

        ((PlayerFaction)fPlayer.getFaction()).disband();
        Chat.send(sender, "&aYou have successfully disbanded your faction.");
    }
}
