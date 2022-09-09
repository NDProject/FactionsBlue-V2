package me.zysea.factions.commands.internal;

import me.zysea.factions.util.Chat;
import org.bukkit.command.CommandSender;

public class CmdFactions extends FactionsCommand {

    public CmdFactions(){
        addChildren(new CmdCreate());
        addChildren(new CmdAlly());
        addChildren(new CmdInfo());
        addChildren(new CmdMap());
        addChildren(new CmdClaim());
        addChildren(new CmdRule());
        addChildren(new CmdDisband());
        addChildren(new CmdBypass());
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Chat.send(sender, "&6&lFactions Help");
        for(SubCommand sub: getChildren()){
            Chat.send(sender, "&e/f " + sub.getName());
        }

        sender.sendMessage("Help Command!");
    }
}
