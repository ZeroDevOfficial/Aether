package Aether.tasks;

import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.network.protocol.ChangeDimensionPacket;
import cn.nukkit.scheduler.NukkitRunnable;

public class changeDimensionTask extends NukkitRunnable {

    private Main plugin;
    private Player player;
    private int dimension;
    private String world;

    public changeDimensionTask(Main main, Player player, int dimension, String world) {
        setPlugin(main);
        this.player = player;
        this.dimension = dimension;
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
        if (!this.player.isOnline()) {
            this.cancel();
        } else {
            ChangeDimensionPacket pk = new ChangeDimensionPacket();
            pk.dimension = this.dimension;
            pk.x = (float) player.x;
            pk.y = (float) player.y;
            pk.z = (float) player.z;
            pk.respawn = false;
            this.player.dataPacket(pk);
            new Aether.tasks.changeWorldTask(getPlugin(), this.player, this.world).runTaskLater(getPlugin(), 5);
        }
    }
}
