package me.zysea.factions;

import com.google.gson.Gson;
import me.zysea.factions.commands.internal.CmdFactions;
import me.zysea.factions.core.persist.*;
import me.zysea.factions.listener.ChatListener;
import me.zysea.factions.listener.FactionTerritoryListener;
import me.zysea.factions.listener.FlagListener;
import org.bukkit.plugin.java.JavaPlugin;

public class FactionsBlue extends JavaPlugin {

    private final Gson gson = new Gson();
    private static FactionsBlue instance;
    private CacheClaims cacheClaims;
    private CacheFactions cacheFactions;
    private CachePlayers cachePlayers;

    public void onEnable(){
        saveDefaultConfig();
        instance = this;
        cacheClaims = new CacheClaims();
        cacheFactions = new CacheFactions();
        cachePlayers = new CachePlayers();


        cacheFactions.load();
        cachePlayers.load();
        cacheClaims.load();

        registerEvents();
        getCommand("factions").setExecutor(new CmdFactions());
    }


    public void onDisable(){
        cacheFactions.save(true);
        cachePlayers.save(true);
        cacheClaims.save(true);
    }

    public static FactionsBlue getInstance(){
        return instance;
    }

    public CacheClaims getCacheClaims() {
        return cacheClaims;
    }

    public CacheFactions getCacheFactions() {
        return cacheFactions;
    }

    public CachePlayers getCachePlayers() {
        return cachePlayers;
    }

    public Gson getGson(){
        return gson;
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new ChatListener(cachePlayers), this);
        getServer().getPluginManager().registerEvents(new FlagListener(cachePlayers), this);
        getServer().getPluginManager().registerEvents(new FactionTerritoryListener(cachePlayers), this);
    }
}
