package me.zysea.factions.core.persist;

import com.google.gson.reflect.TypeToken;
import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.defaults.factions.PlayerFaction;
import me.zysea.factions.objects.defaults.factions.Safezone;
import me.zysea.factions.objects.defaults.factions.Warzone;
import me.zysea.factions.objects.defaults.factions.Wilderness;
import me.zysea.factions.objects.Faction;
import me.zysea.factions.util.DiscUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CacheFactions extends Cache<String, Faction> {


    public CacheFactions(){
        super("factions");
        put("safezone", Safezone.get());
        put("warzone", Warzone.get());
        put("wilderness", Wilderness.get());
    }

    public Faction getBy(UUID playerId){
        Faction faction = null;

        for(Faction entry: getValues()){
            if(entry.isNormal() && ((PlayerFaction)entry).getMembers().isMember(playerId)) {
                faction = entry;
                break;
            }
        }

        return faction == null ? get("wilderness") : faction;
    }

    public Faction getBy(String name){
        Faction faction = null;

        for(Faction entry: getValues()){
            if(entry.getName().equalsIgnoreCase(name)) {
                faction = entry;
                break;
            }
        }

        return faction;
    }


    public void load() {
        Map<String, Faction> factions = read();
        if (factions == null)
            return;

        putAll(factions);
    }

    public void save(boolean sync){
        Map<String, PlayerFaction> factionsToSave = new HashMap<>();
        getValues().stream().filter(Faction::isNormal).forEach(f -> factionsToSave.put(f.getId(), (PlayerFaction)f));
        write(getFile(), factionsToSave, sync);
    }

    private boolean write(File target, Map<String, PlayerFaction> factionsToSave, boolean sync) {
       return DiscUtil.writeCatch(target, FactionsBlue.getInstance().getGson().toJson(factionsToSave), sync);
    }

    private Map<String, Faction> read() {
        if (!getFile().exists())
            return new HashMap<>();

        String content = DiscUtil.readCatch(getFile());
        if (content == null)
            return null;

        return FactionsBlue.getInstance().getGson().fromJson(content, new TypeToken<Map<String, PlayerFaction>>() {
        }.getType());
    }


}
