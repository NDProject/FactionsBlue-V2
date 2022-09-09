package me.zysea.factions.objects.defaults.roles;

import me.zysea.factions.objects.Role;

public class Member extends Role {

    public Member() {
        super("Member");
        setRule("build", false);
        setRule("break", false);
        setRule("claim", false);
    }
}
