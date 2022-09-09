package me.zysea.factions.util;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.Faction;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashSet;
import java.util.Set;

public class FactionsUtil {

    private static Set<String> systemFactions = new HashSet<>();

    static {
        systemFactions.add("wilderness");
        systemFactions.add("warzone");
        systemFactions.add("safezone");
    }

    public static boolean addSystemFaction(String id){
        return systemFactions.add(id);
    }

    public static boolean isSystemFaction(String id){
        return systemFactions.contains(id);
    }

    public static Faction getFaction(String name){
        Faction result = FactionsBlue.getInstance().getCacheFactions().getBy(name);

        if(result == null) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);

            if (offlinePlayer.isOnline() || !offlinePlayer.isOnline() && offlinePlayer.hasPlayedBefore())
                result = FactionsBlue.getInstance().getCachePlayers().get(offlinePlayer.getUniqueId()).getFaction();
            else result = FactionsBlue.getInstance().getCacheFactions().get(name);
        }

        return result;
    }



}
