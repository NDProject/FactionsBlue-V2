package me.zysea.factions.objects;

import me.zysea.factions.core.FactionsObject;
import me.zysea.factions.core.Relation;
import me.zysea.factions.FactionsBlue;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class Claim extends FactionsObject<String> {

    private String world;
    private int x;
    private int z;
    private Faction faction;

    public Claim(Location location){
        this(location.getWorld().getName(), location.getBlockX()>>4, location.getBlockZ()>>4);
    }

    public Claim(String world, int x, int z){
        this.world = world;
        this.x = x;
        this.z = z;
        /*
        bless hotel toss ankle melt piano top possible library water credit forum
         */
    }

    @Override
    public String toString(){
        return world + ";"+x+";"+z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return x == claim.x &&
                z == claim.z &&
                world.equals(claim.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, z);
    }

    public Faction getFaction() {
        if(faction == null)
            this.faction = FactionsBlue.getInstance().getCacheClaims().getOwner(this);

        String id = getFactionId();
        if(id == null || !getFactionId().equals(id))
            setId(faction.getId());

        return faction;
    }


    @Override
    public String getFactionId() {
        if(faction == null)
            getFaction();

        return getId();
    }

    @Override
    public Relation getRelationTo(FactionsObject factionsObject) {
        return getFaction().getRelationTo(factionsObject);
    }

    public Claim getRelative(int x, int z){
        return new Claim(world, this.x+x, this.z+z);
    }
}
