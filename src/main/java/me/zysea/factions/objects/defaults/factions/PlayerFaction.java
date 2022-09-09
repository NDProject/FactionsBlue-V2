package me.zysea.factions.objects.defaults.factions;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.core.Members;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.Faction;
import me.zysea.factions.util.Chat;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerFaction extends Faction {

    private Members members;

    public PlayerFaction(String id, String name) {
        super(id, name);
    }
    public PlayerFaction(String name) {
        super(name);
    }

    public void broadcast(String msg){
        for(OfflinePlayer offlinePlayer: getMembers().getMembers(true)){
            Player player = offlinePlayer.getPlayer();

            if(player == null)
                continue;

            Chat.send(player, msg);
        }
    }

    public void unclaimAll(){
        FactionsBlue instance = FactionsBlue.getInstance();
        instance.getCacheClaims().sortByValue(getFactionId()).forEach(instance.getCacheClaims()::del);
    }

    public void disband(){
        FactionsBlue instance = FactionsBlue.getInstance();
        unclaimAll();

        members.getMembers().entrySet().forEach(e -> instance.getCachePlayers().get(e.getKey()).setFaction(null));
        instance.getCacheFactions().del(getFactionId());
    }

    public boolean join(FPlayer fPlayer){
        if(fPlayer.hasFaction())
            return false;

        getMembers().add(fPlayer.getId(), getMembers().getRole("Member"));
        fPlayer.setFaction(this);
        return true;
    }


    public Members getMembers() {
        if(members == null)
            members = new Members();

        return members;
    }

}
