package me.zysea.factions.objects.defaults.factions;

import me.zysea.factions.core.flags.Flags;
import me.zysea.factions.objects.Faction;

public class Safezone extends Faction {

    private static Safezone instance;

    public Safezone() {
        super("safezone", "Safezone");
        addFlag(Flags.DENY_BREAK);
        addFlag(Flags.DENY_BUILD);
        addFlag(Flags.DENY_TNT_EXPLOSION);
        addFlag(Flags.DENY_MOB_EXPLOSION);
        addFlag(Flags.DENY_PVP);
    }

    public static Safezone get(){
        if(instance == null)
            instance = new Safezone();

        return instance;
    }
}
