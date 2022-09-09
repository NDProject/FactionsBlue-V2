package me.zysea.factions.commands.internal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public abstract class FactionsCommand extends CommandBase implements CommandExecutor {

    public FactionsCommand() {
        super("factions", "Main command", "f", "faction", "fac", "facs");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            SubCommand subCommand = getChildren(args[0]);
            if(subCommand != null){
                if(subCommand.isPlayersOnly() && !(sender instanceof Player)){
                    sender.sendMessage("This command is for players only!");
                    return true;
                }

                if(subCommand.hasPermission() && !sender.hasPermission(subCommand.getPermission())){
                    sender.sendMessage("Insufficient permission.");
                    return true;
                }

                subCommand.onCommand(sender, args.length >= 2 ? Arrays.copyOfRange(args, 1, args.length) : new String[0]);
                return true;
            }
        }

        onCommand(sender, args);
        return true;
    }
}
