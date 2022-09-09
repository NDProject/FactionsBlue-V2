package me.zysea.factions.commands.internal;

import org.bukkit.command.CommandSender;

import java.util.LinkedList;
import java.util.List;

public abstract class CommandBase {

    private String name;
    private String description;
    private String[] aliases;

    private List<SubCommand> children = new LinkedList<>();

    public CommandBase(String name, String description, String... aliases){
        this.name = name;
        this.description = description;
        this.aliases = aliases;
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

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public boolean addChildren(SubCommand sub){
        if(getChildren(sub.getName()) != null)
            return false;

        return children.add(sub);
    }

    public List<SubCommand> getChildren(){
        return children;
    }

    public SubCommand getChildren(String s){
        for(SubCommand subCommand: children){
            if(subCommand.matches(s))
                return subCommand;
        }

        return null;
    }

    public boolean matches(String s){
        if(name.equalsIgnoreCase(s))
            return true;

        for(String alias: aliases){
            if(s.equalsIgnoreCase(alias))
                return true;
        }

        return false;
    }

    public abstract void onCommand(CommandSender sender, String[] args);

}
