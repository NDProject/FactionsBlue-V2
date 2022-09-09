package me.zysea.factions.objects;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.core.FactionsObject;
import me.zysea.factions.core.flags.Flags;
import me.zysea.factions.core.Members;
import me.zysea.factions.core.Relation;
import me.zysea.factions.core.flags.InteractiveFlag;
import me.zysea.factions.objects.defaults.factions.Custom;
import me.zysea.factions.objects.defaults.factions.Safezone;
import me.zysea.factions.objects.defaults.factions.Warzone;
import me.zysea.factions.objects.defaults.factions.Wilderness;
import me.zysea.factions.util.Chat;
import me.zysea.factions.util.FactionsUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Faction extends FactionsObject<String> {

    private String name;

    private Map<String, Relation> relations = new HashMap<>();
    private Set<Flags> flags = new HashSet<>();
    private Map<String, InteractiveFlag> interactiveFlags = new HashMap<>();

    public Faction(String id, String name) {
        super(id);
        this.name = name;
    }

    public Faction(String name){
        super(RandomStringUtils.random(ThreadLocalRandom.current().nextInt(6, 11)+1, true, true));
        this.name = name;
    }

    @Override
    public String getFactionId() {
        return getId();
    }

    public Relation getRelationTo(FactionsObject factionsObject) {
        if(factionsObject.getFactionId().equals(getId()) )
            return null;
        if(FactionsUtil.isSystemFaction(factionsObject.getFactionId()))
            return null;

       return relations.get(factionsObject.getFactionId());
    }

    public boolean hasFlag(Flags flag){
        return flags.contains(flag);
    }

    public boolean hasFlag(String interactiveFlag){
        return interactiveFlags.containsKey(interactiveFlag);
    }

    public boolean delFlag(String flag){
        return flags.remove(flag) || interactiveFlags.remove(flag) != null;
    }

    public boolean addFlag(Flags flag){
        for(Flags factionFlag: flags){
            if(factionFlag == flag || factionFlag.conflictsWith(flag))
                return false;
        }

        return flags.add(flag);
    }

    public boolean addFlag(InteractiveFlag interactiveFlag){
        interactiveFlags.put(interactiveFlag.getName(), interactiveFlag);
        return true;
    }

    public InteractiveFlag getInteractiveFlag(String name){
        return interactiveFlags.get(name);
    }

    public boolean isWilderness(){
        return this instanceof Wilderness;
    }

    public boolean isSafezone(){
        return this instanceof Safezone;
    }

    public boolean isWarzone(){
        return this instanceof Warzone;
    }

    public boolean isCustom(){
        return this instanceof Custom;
    }

    public boolean isNormal(){
        return !isSafezone() && !isWarzone() && !isWilderness();
    }

    public void addRelationTo(Faction faction, Relation relation){
        relations.put(faction.getId(), relation);
    }

    public boolean delRelationTo(Faction faction){
        Relation relation = relations.remove(faction.getId());
        return relation != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
