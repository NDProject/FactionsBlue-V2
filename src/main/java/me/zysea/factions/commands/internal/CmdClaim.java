package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.Claim;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.util.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class CmdClaim extends SubCommand{
    public CmdClaim() {
        super("claim", "claim land", "c");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());
        String fId = args.length > 0 && fPlayer.isBypass() && (args[0].equalsIgnoreCase("warzone") ||
                args[0].equalsIgnoreCase("safezone")) ? args[0].toLowerCase() : fPlayer.getFactionId();

        Claim current = new Claim(player.getLocation());

        // Check if player is bypassing restrictions otherwise prevent action.
        if(!fPlayer.isBypass()){
            if(!current.getFaction().isWilderness()){
                Chat.send(sender, "&cThis land has already been claimed by &l"+current.getFaction().getName());
                return;
            }

            if(!fPlayer.getRole().hasPermission("claim")){
                Chat.send(sender, "&cYou do not have permission to claim land for your faction.");
                return;
            }
        }

        FactionsBlue.getInstance().getCacheClaims().put(current.toString(), fId);
        Chat.send(sender, "&aYou have claimed this land!");
    }
}
