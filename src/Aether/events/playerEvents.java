package Aether.events;

import Aether.AetherPlayer;
import Aether.Main;
import Aether.entity.FloatingText;
import Aether.entity.Npc;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class playerEvents implements Listener {

    private Main getPlugin() {
        return Main.getInstance();
    }

    @EventHandler
    public void playerCreation(cn.nukkit.event.player.PlayerCreationEvent event) {
        event.setPlayerClass(Aether.AetherPlayer.class);
    }

    @EventHandler
    public void join(cn.nukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
        ((AetherPlayer) player).getConfig().set("last_login", new SimpleDateFormat("MM/dd/yyyy hh:mm a zzz").format(new Date()));

        ((AetherPlayer) player).setupForTeleport();
        new Aether.tasks.sendHub(player, true, TextFormat.YELLOW + "Welcome to", getPlugin().getPrefix()).runTaskLater(getPlugin(), 40);
    }

    @EventHandler
    public void playerTeleport(cn.nukkit.event.player.PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (event.getTo().getLevel() != getPlugin().getDefaultLevel()) {
            for (Npc npc : getPlugin().npc.values()) {
                npc.despawnFrom(player);
            }
            for (FloatingText text : getPlugin().texts.values()) {
                text.despawnFrom(player);
            }
        }
    }
}