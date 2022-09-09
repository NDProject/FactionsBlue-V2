package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.MapHandler;
import me.zysea.factions.objects.Claim;
import me.zysea.factions.objects.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMap extends SubCommand{
    private MapHandler mapHandler = new MapHandler();

    public CmdMap() {
        super("map", "display map","m");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());
        for(String s: mapHandler.getMap(fPlayer.getFaction(), new Claim(player.getLocation()), 6)){
            player.sendMessage(s);
        }
    }
}
