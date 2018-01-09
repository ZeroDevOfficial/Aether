package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.TextFormat;

public class sendHub extends NukkitRunnable {

    private Player player;
    private boolean join;
    private String title;
    private String subTitle;

    public sendHub(Player player, boolean join, String title, String subTitle) {
        this.player = player;
        this.join = join;
        this.title = title;
        this.subTitle = subTitle;
    }

    private Main getPlugin() {
        return Aether.Main.getInstance();
    }

    @Override
    public void run() {
        if (!player.isOnline()) {
            this.cancel();
        } else {
            if (((AetherPlayer) player).currentBossBar != null) {
                ((AetherPlayer) player).currentBossBar.destroy();
            }

            if (join == false) {
                new changeDimensionTask(getPlugin(), player, 0, getPlugin().getServer().getLevelByName("temp").getName()).runTaskLater(getPlugin(), 20);
            }

            new NukkitRunnable() {
                @Override
                public void run() {
                    if (!player.isOnline()) {
                        this.cancel();
                    } else {
                        player.setGamemode(2);
                        ((AetherPlayer) player).setupForTeleport();

                        new NukkitRunnable() {
                            @Override
                            public void run() {
                                if (!player.isOnline()) {
                                    this.cancel();
                                } else {
                                    player.sendTitle(TextFormat.YELLOW + "Teleporting...", TextFormat.AQUA + "Please Wait!");
                                    new changeDimensionTask(getPlugin(), player, 2, getPlugin().getDefaultLevel().getName()).runTaskLater(getPlugin(), 120);

                                    new NukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (!player.isOnline()) {
                                                this.cancel();
                                            } else {
                                                ((AetherPlayer) player).sendHub(true, title, subTitle);
                                            }
                                        }
                                    }.runTaskLater(getPlugin(), 150);
                                }
                            }
                        }.runTaskLater(getPlugin(), 120);
                    }
                }
            }.runTaskLater(getPlugin(), 120);
        }
    }
}
