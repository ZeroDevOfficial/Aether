package Aether.events;

import Aether.AetherPlayer;
import Aether.Main;
import Aether.tasks.changeDimensionTask;
import Aether.tasks.sendHub;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.DummyBossBar;
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

        player.addEffect(new Effect(Effect.INVISIBILITY, "Invisibility", 0, 0, 0).setDuration(100000));

        player.setImmobile(true);
        player.setEnableClientCommand(false);
        player.setCheckMovement(false);

        new NukkitRunnable() {
            @Override
            public void run() {
                player.sendTitle(TextFormat.YELLOW + "Teleporting...", TextFormat.AQUA + "Please Wait!");

                new changeDimensionTask(getPlugin(), player, 2, getPlugin().getDefaultLevel().getName()).runTaskLater(getPlugin(), 100);
                new sendHub(player, false, TextFormat.YELLOW + "Welcome to", getPlugin().getPrefix()).runTaskLater(getPlugin(), 120);
            }
        }.runTaskLater(getPlugin(), 60);

        new NukkitRunnable() {
            @Override
            public void run() {
                ((AetherPlayer) player).currentBossBar = new DummyBossBar.Builder(player).text("").length(100).build();
                player.createBossBar(((AetherPlayer) player).currentBossBar);
            }
        }.runTaskLater(getPlugin(), 175);
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