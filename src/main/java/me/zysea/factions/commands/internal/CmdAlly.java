package me.zysea.factions.commands.internal;

import me.zysea.factions.FactionsBlue;
import me.zysea.factions.core.Relation;
import me.zysea.factions.objects.defaults.factions.PlayerFaction;
import me.zysea.factions.objects.defaults.relations.Alliance;
import me.zysea.factions.objects.FPlayer;
import me.zysea.factions.objects.Faction;
import me.zysea.factions.util.Chat;
import me.zysea.factions.util.FactionsUtil;
import me.zysea.factions.util.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * TODO: Recode
 */
public class CmdAlly extends SubCommand {


    public CmdAlly() {
        super("ally", "Ally a faction", "ally");
        super.setPlayersOnly(true);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        FPlayer fPlayer = FactionsBlue.getInstance().getCachePlayers().get(player.getUniqueId());
        Faction target = FactionsUtil.getFaction(args[0]);

        if (target == null)  {
            Chat.send(player, "&cInvalid faction!");
            return;
        }

        if(!fPlayer.getFaction().isNormal() || !target.isNormal()){
            Chat.send(player, "&cCannot set relation to default or custom factions.");
            return;
        }
        PlayerFaction pF = (PlayerFaction) fPlayer.getFaction();
        PlayerFaction pTarget = (PlayerFaction)target;

        Relation myRelation = fPlayer.getRelationTo(pF);
        Relation targetRelation = target.getRelationTo(pTarget);

        if(myRelation != null & targetRelation == null){
            revokeRequest(pF, pTarget);
            return;
        }

        if (myRelation == null && targetRelation == null) {
            request(pF, pTarget);
            return;
        }

        if (myRelation != null && targetRelation != null) {
            revoke(pF, pTarget);
            return;
        }

        if (myRelation == null) {
            accept(pTarget, pF, targetRelation);
            return;
        }

        if (myRelation.isConfirmed()) {
            revoke(pF, pTarget);
            return;
        }

        accept(pTarget, pF, myRelation);

           // request(fPlayer.getFaction(), target);
    }



    public void revokeRequest(PlayerFaction sender, PlayerFaction target){
        sender.broadcast("&cAlliance request to " + target.getName() + " revoked!");
        sender.delRelationTo(target);
    }

    public void request(PlayerFaction sender, PlayerFaction receiver){
        receiver.broadcast(Messages.allyFactionReceiver.replace("{faction}", sender.getName()));
        sender.broadcast(Messages.allyFactionSender.replace("{faction}", receiver.getName()));
        sender.addRelationTo(receiver, new Alliance());
    }

    public void revoke(PlayerFaction sender, PlayerFaction receiver){
        receiver.broadcast("&cYou're no longer allied to " + sender.getName());
        sender.broadcast("&cYou're no longer allied to " + receiver.getName());
        sender.delRelationTo(receiver);
        receiver.delRelationTo(sender);
    }

    public void accept(PlayerFaction sender, PlayerFaction receiver, Relation relation){
        sender.broadcast(Messages.allyFactionConfirm.replace("{faction}", receiver.getName()));
        receiver.broadcast(Messages.allyFactionConfirm.replace("{faction}", sender.getName()));
        relation.setConfirmed(true);
        receiver.addRelationTo(sender, relation);
    }


}
