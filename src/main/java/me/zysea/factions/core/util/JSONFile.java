package me.zysea.factions.core.util;

import com.google.gson.Gson;
import me.zysea.factions.FactionsBlue;

import java.io.File;

public abstract class JSONFile {

    private Gson gson;
    private File file;

    public JSONFile(String name){
        gson = FactionsBlue.getInstance().getGson();
        this.file = new File(FactionsBlue.getInstance().getDataFolder(), name+".json");
    }

    public abstract void load();
    public abstract void save(boolean sync);

    public Gson getGson() {
        return gson;
    }

    public File getFile() {
        return file;
    }
}
