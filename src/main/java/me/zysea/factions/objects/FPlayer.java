package me.zysea.factions.objects;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.core.FactionsObject;
import me.zysea.factions.core.Relation;
import me.zysea.factions.objects.defaults.factions.PlayerFaction;
import org.bukkit.Location;

import java.util.UUID;

public class FPlayer extends FactionsObject<UUID>{

    private transient Faction faction;
    private String factionId = "wilderness";
    private boolean bypass = false;
    private transient Role role;
    private transient Claim location;

    public FPlayer(UUID id) {
        super(id);
    }

    @Override
    public String getFactionId() {
        return factionId;
    }

    public Relation getRelationTo(FactionsObject factionsObject) {
        return getFaction().getRelationTo(factionsObject);
    }

    public Faction getFaction(){
        if(faction == null)
            setFaction(FactionsBlue.getInstance().getCacheFactions().getBy(getId()));

        return faction;
    }

    public void setFaction(Faction faction) {
        if(faction == null){
            this.faction = null;
            this.factionId = "wilderness";
            return;
        }
        this.faction = faction;
        this.factionId = faction.getId();
    }

    public boolean hasFaction(){
        return !getFaction().isWilderness();
    }

    public Role getRole(){
        if(!faction.isNormal())
            return null;

        if(role == null && hasFaction())
            role = ((PlayerFaction)getFaction()).getMembers().getRole(this);

        return role;
    }

    public Claim getLocation(){
        return location;
    }

    public void updateLocation(Location location){
        this.location = new Claim(location);
    }


    public boolean isBypass() {
        return bypass;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }
}
