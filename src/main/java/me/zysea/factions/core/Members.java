package me.zysea.factions.core;

import me.zysea.factions.objects.defaults.roles.Leader;
import me.zysea.factions.objects.defaults.roles.Member;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.Role;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.*;

public class Members {

    private List<Role> roles = new LinkedList<>();
    private Map<UUID, Role> members = new HashMap<>();

    public Members(){
        roles.add(new Leader());
        roles.add(new Member());
    }

    public boolean isMember(UUID playerId){
        return members.containsKey(playerId);
    }

    public boolean add(UUID playerId, Role role){
        if(!roles.contains(role))
            return false;

        members.put(playerId, role);
        return true;
    }

    public void del(UUID playerId){
        members.remove(playerId);
    }

    public boolean addRole(Role role){
        if(getRole(role.getName()) != null)
            return false;

        return roles.add(role);
    }

    public Role getRole(UUID playerId){
        return members.get(playerId);
    }

    public Role getRole(String name){
        return roles.stream().filter(r -> r.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Role getRole(FPlayer fPlayer){
        return getRole(fPlayer.getId());
    }

    public Map<UUID, Role> getMembers(){
        return members;
    }

    public List<OfflinePlayer> getMembers(boolean online){
        List<OfflinePlayer> result = new ArrayList<>();
        for(UUID uuid: this.members.keySet()){
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            if(online && !offlinePlayer.isOnline())
                continue;

            result.add(offlinePlayer);
        }

        return result;
    }

}
