package me.zysea.factions.objects.defaults.roles;

import me.zysea.factions.objects.Role;

public class Leader extends Role {
    public Leader() {
        super("Leader");
        setRule("build", true);
        setRule("break", true);
        setRule("claim", true);
        setRule("rules", true);
    }
}
