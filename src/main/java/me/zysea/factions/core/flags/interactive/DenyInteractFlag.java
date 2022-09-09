package me.zysea.factions.core.flags.interactive;

import me.zysea.factions.core.flags.InteractiveFlag;
import org.bukkit.Material;

public class DenyInteractFlag extends InteractiveFlag<Material> {

    public DenyInteractFlag() {
        super("deny_interact"); // names are final!
    }
}
