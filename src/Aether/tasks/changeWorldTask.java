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
    private String world;

    changeWorldTask(Player player, String world) {
        this.player = player;
        this.world = world;
    }

    private Main getPlugin() {
        return Main.getInstance();
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
