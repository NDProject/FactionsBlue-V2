package me.zysea.factions.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Chat {

    public static void send(CommandSender target, String msg){
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
