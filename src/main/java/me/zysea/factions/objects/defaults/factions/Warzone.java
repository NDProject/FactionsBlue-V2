package me.zysea.factions.objects.defaults.factions;

import me.zysea.factions.core.flags.Flags;
import me.zysea.factions.objects.Faction;

public class Warzone extends Faction {

    private static Warzone instance;

    public Warzone() {
        super("warzone", "Warzone");
        addFlag(Flags.DENY_BREAK);
        addFlag(Flags.DENY_BUILD);
        addFlag(Flags.DENY_TNT_EXPLOSION);
        addFlag(Flags.DENY_MOB_EXPLOSION);
    }

    public static Warzone get(){
        if(instance == null)
            instance = new Warzone();

        return instance;
    }
}
