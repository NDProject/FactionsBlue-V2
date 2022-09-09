package me.zysea.factions.commands.internal;


public abstract class SubCommand extends CommandBase {

    private String permission;
    private boolean playersOnly = false;

    public SubCommand(String name, String description, String... aliases) {
        super(name, description, aliases);
    }


    public boolean hasPermission(){
        return permission != null;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isPlayersOnly() {
        return playersOnly;
    }

    public void setPlayersOnly(boolean playersOnly) {
        this.playersOnly = playersOnly;
    }
}
