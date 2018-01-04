package Aether.tasks;

import Aether.AetherPlayer;
import cn.nukkit.Player;
import cn.nukkit.scheduler.NukkitRunnable;

public class sendHub extends NukkitRunnable {

    private Player player;
    private boolean teleport;
    private String title;
    private String subTitle;

    public sendHub(Player player, boolean teleport, String title, String subTitle) {
        this.player = player;
        this.teleport = teleport;
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.cancel();
        } else {
            ((AetherPlayer) this.player).sendHub(teleport, title, subTitle);
        }
    }
}
