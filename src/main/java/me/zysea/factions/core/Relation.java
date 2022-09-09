package me.zysea.factions.core;

public abstract class Relation {
    private String name;
    private String description;
    private boolean confirmed = false;
    private boolean friendly;

    public Relation(String name, String description, boolean friendly){
        this.name = name;
        this.description = description;
        this.friendly = friendly;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public boolean isConfirmed(){
        return confirmed;
    }

    public void setConfirmed(boolean isConfirmed){
        this.confirmed = isConfirmed;
    }
}
