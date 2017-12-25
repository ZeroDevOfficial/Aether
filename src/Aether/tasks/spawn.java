package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import Aether.entity.Npc;
import cn.nukkit.Player;
import cn.nukkit.network.protocol.PlayStatusPacket;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;

import java.util.Map.Entry;

public class spawn extends NukkitRunnable {

    private Player player;
    private Main plugin;

    public spawn(Player player, Main main) {
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
            ((AetherPlayer) this.player).currentBossBar = new DummyBossBar.Builder(this.player).text(getPlugin().getUtils().getBossBars().get("hub").replace("{PLAYERS}", "Online 0")).length(100).build();
            this.player.createBossBar(((AetherPlayer) this.player).currentBossBar);

            this.player.removeAllEffects();
            this.player.teleport(getPlugin().getDefaultLevel().getSafeSpawn());

            PlayStatusPacket pk = new PlayStatusPacket();
            pk.status = 3;
            this.player.dataPacket(pk);

            new Aether.tasks.sendHub((Player) this.player, false, TextFormat.YELLOW + "Welcome to", getPlugin().getPrefix()).runTaskLater(getPlugin(), 30);

            for (Entry<String, Npc> npc : getPlugin().getUtils().getNpcs().entrySet()) {
                npc.getValue().spawnTo(this.player);
            }
        } else {
            this.cancel();
        }
    }
}
