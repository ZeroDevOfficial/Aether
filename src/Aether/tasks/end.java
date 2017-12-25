package Aether.tasks;

import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.network.protocol.ChangeDimensionPacket;
import cn.nukkit.scheduler.NukkitRunnable;

public class end extends NukkitRunnable {

    private Player player;
    private Main plugin;

    public end(Player player, Main main) {
        this.player = player;
        setPlugin(main);
    }

    private Aether.Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (this.player.isOnline()) {
            ChangeDimensionPacket pk = new ChangeDimensionPacket();
            pk.dimension = 2;
            pk.x = (float) player.x;
            pk.y = (float) player.y;
            pk.z = (float) player.z;
            pk.respawn = false;
            this.player.dataPacket(pk);
            new Aether.tasks.spawn((Player) this.player, getPlugin()).runTaskLater(getPlugin(), 60);
        } else {
            this.cancel();
        }
    }
}
