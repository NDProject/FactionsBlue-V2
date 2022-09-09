package me.zysea.factions.core.persist;

import com.google.gson.reflect.TypeToken;
import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.Faction;
import me.zysea.factions.util.DiscUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CachePlayers extends Cache<String, FPlayer> {

    public CachePlayers() {
        super("fplayers");
    }

    public FPlayer get(UUID playerId){
        FPlayer fp = get(playerId.toString());

        if(fp == null) {
            fp = new FPlayer(playerId);
            put(playerId.toString(), fp);
        }

        return fp;
    }

    public void load() {
        Map<String, FPlayer> fplayers = read();
        if (fplayers == null)
            return;

        putAll(fplayers);
    }

    public void save(boolean sync){
        Map<String, FPlayer> playersToSave = new HashMap<>();
        getValues().stream().forEach(f -> playersToSave.put(f.getId().toString(), f));
        write(getFile(), playersToSave, sync);
    }

    private boolean write(File target, Map<String, FPlayer> playersToSave, boolean sync) {
        return DiscUtil.writeCatch(target, FactionsBlue.getInstance().getGson().toJson(playersToSave), sync);
    }

    private Map<String, FPlayer> read() {
        if (!getFile().exists())
            return new HashMap<>();

        String content = DiscUtil.readCatch(getFile());
        if (content == null)
            return null;

        return FactionsBlue.getInstance().getGson().fromJson(content, new TypeToken<Map<String, FPlayer>>() {
        }.getType());
    }

}
