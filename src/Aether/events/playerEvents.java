package Aether.events;

import Aether.AetherPlayer;
import Aether.Main;
import Aether.entity.FloatingText;
import Aether.entity.Npc;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;

public class playerEvents implements Listener {

    private Main plugin;

    public playerEvents(Main main) {
        this.setPlugin(main);
    }

    private Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerCreation(cn.nukkit.event.player.PlayerCreationEvent event) {
        event.setPlayerClass(Aether.AetherPlayer.class);
    }

    @EventHandler
    public void join(cn.nukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");

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

    @EventHandler
    public void entityDamage(cn.nukkit.event.entity.EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            if (entity.getLevel() == getPlugin().getDefaultLevel()) {
                event.setCancelled(true);
            }
            if (entity.getLevel() == getPlugin().getServer().getLevelByName("temp")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void blockBreak(cn.nukkit.event.block.BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getLevel() == getPlugin().getDefaultLevel()) {
            //event.setCancelled(true);
        }
        if (player.getLevel() == getPlugin().getServer().getLevelByName("temp")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlace(cn.nukkit.event.block.BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.getLevel() == getPlugin().getDefaultLevel()) {
            //event.setCancelled(true);
        }
        if (player.getLevel() == getPlugin().getServer().getLevelByName("temp")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void hunger(cn.nukkit.event.player.PlayerFoodLevelChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getLevel() == getPlugin().getDefaultLevel()) {
            event.setCancelled(true);
        }
        if (player.getLevel() == getPlugin().getServer().getLevelByName("temp")) {
            event.setCancelled(true);
        }
    }
}