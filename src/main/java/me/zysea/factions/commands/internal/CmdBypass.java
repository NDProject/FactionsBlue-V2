package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.util.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBypass extends SubCommand{


    public CmdBypass() {
        super("bypass", "admin mode", "b");
        setPlayersOnly(true);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());
        fPlayer.setBypass(!fPlayer.isBypass());
        Chat.send(sender, "&e&lBypassing: " + fPlayer.isBypass());
    }
}
