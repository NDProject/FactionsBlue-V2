package me.zysea.factions.core.flags;

public enum Flags {
    NO_DAMAGE(),
    DENY_MOB_EXPLOSION(),
    DENY_TNT_EXPLOSION(),
    DENY_PVP(),
    DENY_NATURAL_SPAWNING(),
    PERMANENT(),
    NO_FALL_DAMAGE(),
    DENY_BUILD(),
    NO_HUNGER(),
    DENY_BREAK();

    private Flags[] conflicting;

    Flags(Flags... conflicting){
        this.conflicting = conflicting;
    }

    Flags(){
        conflicting = new Flags[0];
    }

    public Flags[] getConflicting(){
        return conflicting;
    }

    public boolean conflictsWith(Flags flag){
        for(Flags conflicting: conflicting){
            if(conflicting == flag)
                return true;
        }

        return false;
    }

}
