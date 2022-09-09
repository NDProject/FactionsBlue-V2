package me.zysea.factions.objects.defaults.factions;

import me.zysea.factions.objects.Faction;

public class Wilderness extends Faction {
    private static Wilderness instance;

    public Wilderness() {
        super("wilderness", "Wilderness");
    }

    public static Wilderness get(){
        if(instance == null)
            instance = new Wilderness();

        return instance;
    }
}
