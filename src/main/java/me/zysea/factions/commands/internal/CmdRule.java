package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.Role;
import me.zysea.factions.objects.defaults.factions.PlayerFaction;
import me.zysea.factions.util.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdRule extends SubCommand{
    public CmdRule() {
        super("rule", "set rules", "r");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player)sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());
        if(fPlayer.hasFaction()){
            Chat.send(sender, "&cYou must be part of a faction.");
            return;
        }
        if(!fPlayer.getRole().hasPermission("rules")){
            Chat.send(sender, "&cInsufficient faction permissions.");
            return;
        }

        if(args.length < 3){
            Chat.send(sender, "&cCurrect Usage: &e/f rule <role> <rule> <boolean>");
            return;
        }



        Role role = ((PlayerFaction)fPlayer.getFaction()).getMembers().getRole(args[0]);
        if(role == null){
            Chat.send(sender, "&cInvalid role");
            return;
        }

        String rule = args[1];
        Boolean value = !args[2].equalsIgnoreCase("false") && !args[2].equalsIgnoreCase("true") ?
                null : Boolean.parseBoolean(args[2]);

        if(value == null){
            Chat.send(sender, "&cInvalid value. Use True or False!");
            return;
        }

        role.setRule(rule, value);
        Chat.send(sender, "&aRule has been updated for &l" + role.getName());
    }
}
