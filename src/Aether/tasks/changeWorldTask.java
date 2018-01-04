package Aether.tasks;

import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.network.protocol.PlayStatusPacket;
import cn.nukkit.scheduler.NukkitRunnable;

/**
 * changeWorldTask is a task tha gets called in changeDimensionTask
 */

public class changeWorldTask extends NukkitRunnable {

    private Player player;
    private Main plugin;
    private String world;

    public changeWorldTask(Main main, Player player, String world) {
        setPlugin(main);
        this.player = player;
        this.world = world;
    }

    private Aether.Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (!this.player.isOnline() && this.world != null) {
            this.cancel();
        } else {
            this.player.teleport(getPlugin().getServer().getLevelByName(this.world).getSafeSpawn());

            PlayStatusPacket pk = new PlayStatusPacket();
            pk.status = 3;
            this.player.dataPacket(pk);

        }
    }
}
