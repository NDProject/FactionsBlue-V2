package me.zysea.factions.core.persist;

import com.google.gson.reflect.TypeToken;
import me.zysea.factions.Claims;
import me.zysea.factions.FactionsBlue;
import me.zysea.factions.objects.Claim;
import me.zysea.factions.objects.Faction;
import me.zysea.factions.objects.defaults.factions.Wilderness;
import me.zysea.factions.util.DiscUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CacheClaims extends Cache<String, String> implements Claims {

    public CacheClaims(){
        super("claims");
    }

    @Override
    public Faction getOwner(Claim claim) {
        String fId = get(claim.toString());
        return fId == null ? Wilderness.get() : FactionsBlue.getInstance().getCacheFactions().get(fId);
    }

    @Override
    public void load() {
        Map<String, String> claims = read();
        if(claims == null)
            return;

        putAll(claims);
    }

    @Override
    public void save(boolean sync) {
        Map<String, String> claimsToSave = getClonedMap();
        write(getFile(), claimsToSave, sync);
    }

    private boolean write(File target, Map<String, String> claimsToSave, boolean sync) {
        return DiscUtil.writeCatch(target, FactionsBlue.getInstance().getGson().toJson(claimsToSave), sync);
    }

    private Map<String, String> read(){
        if(!getFile().exists())
            return new HashMap<>();

        String content = DiscUtil.readCatch(getFile());
        if (content == null)
            return null;

        return FactionsBlue.getInstance().getGson().fromJson(content, new TypeToken<Map<String, String>>() {
        }.getType());
    }
}
