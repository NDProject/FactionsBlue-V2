package me.zysea.factions;

import me.zysea.factions.core.Relation;
import me.zysea.factions.objects.Claim;
import me.zysea.factions.objects.Faction;
import me.zysea.factions.util.Configuration;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapHandler {

    public ArrayList<String> getMap(Faction faction, Claim claim, double inDegrees) {
        ArrayList<String> ret = new ArrayList<String>();
        Faction factionLoc = claim.getFaction();
        //ret.add(P.p.txt.titleize("(" + flocation.getCoordString() + ") " + factionLoc.getTag(faction)));

        int halfWidth = 16 / 2;
        int halfHeight = 16 / 2;
        Claim topLeft = claim.getRelative(-halfWidth, -halfHeight);
        int width = halfWidth * 2 + 1;
        int height = halfHeight * 2 + 1;

       /* if (Conf.showMapFactionKey) {
            height--;
        }*/

        Map<String, Character> fList = new HashMap<String, Character>();
        int chrIdx = 0;

        // For each row
        for (int dz = 0; dz < height; dz++) {
            // Draw and add that row
            String row = "";
            for (int dx = 0; dx < width; dx++) {
                if (dx == halfWidth && dz == halfHeight) {
                    row += ChatColor.AQUA + "+";
                } else {
                    Claim flocationHere = topLeft.getRelative(dx, dz);
                    Faction factionHere = flocationHere.getFaction();
                    Relation relation = faction.getRelationTo(factionHere);
                    if (factionHere.isWilderness()) {
                        row += ChatColor.GRAY + "-";
                    } else if(factionHere == faction){
                        row += ChatColor.GREEN + "=";
                    }else if (factionHere.isSafezone()) {
                        row += ChatColor.GOLD + "+";
                    } else if (factionHere.isWarzone()) {
                        row += ChatColor.DARK_RED + "+";
                    } /*else if (factionHere == faction ||
                            factionHere == factionLoc ||
                            relation.isFriendly() ||
                            (Configuration.showAlly && relation.) ||
                            (Conf.showEnemyFactionsOnMap && relation.equals(Relation.ENEMY))) {
                        if (!fList.containsKey(factionHere.getTag())) {
                            fList.put(factionHere.getTag(), Conf.mapKeyChrs[Math.min(chrIdx++, Conf.mapKeyChrs.length - 1)]);
                        }
                        char tag = fList.get(factionHere.getTag());
                        row += factionHere.getColorTo(faction) + "" + tag;
                    }*/ else {
                        row += ChatColor.GRAY + "-";
                    }
                }
            }
            ret.add(row);
        }

        // Get the compass
       /* ArrayList<String> asciiCompass = AsciiCompass.getAsciiCompass(inDegrees, ChatColor.RED, P.p.txt.parse("<a>"));

        // Add the compass
        ret.set(1, asciiCompass.get(0) + ret.get(1).substring(3 * 3));
        ret.set(2, asciiCompass.get(1) + ret.get(2).substring(3 * 3));
        ret.set(3, asciiCompass.get(2) + ret.get(3).substring(3 * 3));*/

        // Add the faction key
        /*
        if (Conf.showMapFactionKey) {
            String fRow = "";
            for (String key : fList.keySet()) {
                fRow += String.format("%s%s: %s ", ChatColor.GRAY, fList.get(key), key);
            }
            ret.add(fRow);
        }*/

        return ret;
    }
}
