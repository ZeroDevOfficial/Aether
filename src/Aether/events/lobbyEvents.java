package Aether.events;

import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;

public class lobbyEvents implements Listener {

    private String protectedMaps[] = new String[]{//TODO add more maps
            "hub",
            "temp"
    };

    private Main getPlugin() {
        return Main.getInstance();
    }

    @EventHandler
    public void protectedMapEntityDamage(cn.nukkit.event.entity.EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            for (String lvl : protectedMaps) {
                if (getPlugin().getServer().isLevelLoaded(lvl)) {
                    if (entity.getLevel() == getPlugin().getServer().getLevelByName(lvl)) {
                        event.setCancelled();
                    }
                }
            }
        }
    }

    @EventHandler
    public void protectedMapBlockBreak(cn.nukkit.event.block.BlockBreakEvent event) {
        Player player = event.getPlayer();
        for (String lvl : protectedMaps) {
            if (getPlugin().getServer().isLevelLoaded(lvl)) {
                if (lvl != "hub" && getPlugin().getLobby() != "Dev Lobby") {
                    if (player.getLevel() == getPlugin().getServer().getLevelByName(lvl)) {
                        event.setCancelled();
                    }
                }
            }
        }
    }

    @EventHandler
    public void protectedMapBlockPlace(cn.nukkit.event.block.BlockPlaceEvent event) {
        Player player = event.getPlayer();
        for (String lvl : protectedMaps) {
            if (getPlugin().getServer().isLevelLoaded(lvl)) {
                if (lvl != "hub" && getPlugin().getLobby() != "Dev Lobby") {
                    if (player.getLevel() == getPlugin().getServer().getLevelByName(lvl)) {
                        event.setCancelled();
                    }
                }
            }
        }
    }

    @EventHandler
    public void protectedMapHungerEvent(cn.nukkit.event.player.PlayerFoodLevelChangeEvent event) {
        Player player = event.getPlayer();
        for (String lvl : protectedMaps) {
            if (getPlugin().getServer().isLevelLoaded(lvl)) {
                if (player.getLevel() == getPlugin().getServer().getLevelByName(lvl)) {
                    event.setCancelled();
                }
            }
        }
    }
}
