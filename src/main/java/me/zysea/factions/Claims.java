package me.zysea.factions;

import me.zysea.factions.objects.Claim;
import me.zysea.factions.objects.Faction;

public interface Claims {

    Faction getOwner(Claim claim);

}
