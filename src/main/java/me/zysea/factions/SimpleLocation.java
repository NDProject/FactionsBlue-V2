package me.zysea.factions;

import org.bukkit.Location;
import sun.java2d.pipe.SpanShapeRenderer;

public class SimpleLocation {

    private String world;
    private double x;
    private double y;
    private double z;

    public SimpleLocation(String world, double x, double y, double z){
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SimpleLocation(Location location){
        this(location.getWorld().getName(), location.getX(), location.getY(), location.getZ());
    }

}
