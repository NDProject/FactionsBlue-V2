package me.zysea.factions.core;

public abstract class FactionsObject<T> implements RelationParticipator{

    private T id;

    public FactionsObject(T id){
        this.id = id;
    }

    protected FactionsObject(){

    }

    public T getId() {
        return id;
    }

    protected void setId(T id){
        this.id = id;
    }

    public abstract String getFactionId();

}
