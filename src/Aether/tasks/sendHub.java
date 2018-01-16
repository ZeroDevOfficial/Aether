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
        return Main.getInstance();
    }

    @Override
    public void run() {
        if (!player.isOnline()) {
            this.cancel();
        } else {
            if (((AetherPlayer) player).currentBossBar != null) {
                ((AetherPlayer) player).currentBossBar.destroy();
            }

            if (!join) {
                new changeDimensionTask(player, 0, getPlugin().getServer().getLevelByName("temp").getName()).runTaskLater(getPlugin(), 20);
            }

            new NukkitRunnable() {
                @Override
                public void run() {
                    if (!player.isOnline()) {
                        this.cancel();
                    } else {
                        player.setGamemode(2);

                        new NukkitRunnable() {
                            @Override
                            public void run() {
                                if (!player.isOnline()) {
                                    this.cancel();
                                } else {
                                    player.sendTitle(TextFormat.YELLOW + "Teleporting...", TextFormat.AQUA + "Please Wait!");
                                    new changeDimensionTask(player, 2, getPlugin().getDefaultLevel().getName()).runTaskLater(getPlugin(), 120);

                                    new NukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (!player.isOnline()) {
                                                this.cancel();
                                            } else {
                                                if (join) {
                                                    ((AetherPlayer) player).saveSkin();
                                                    player.removeAllEffects();
                                                    player.sendMessage(TextFormat.DARK_GRAY + "========================\n\n");
                                                    player.sendMessage(
                                                            TextFormat.YELLOW + "Welcome to " + TextFormat.AQUA + TextFormat.BOLD + "Aether Network\n\n" +
                                                                    TextFormat.YELLOW + "You are in " + getPlugin().getLobby() + "\n\n" +
                                                                    TextFormat.AQUA + "" + getPlugin().getServer().getOnlinePlayers().size() + TextFormat.YELLOW + " Player(s) Online\n\n");
                                                    player.sendMessage(TextFormat.DARK_GRAY + "========================\n\n");
                                                    ((AetherPlayer) player).sendHub(true, TextFormat.YELLOW + "Welcome", TextFormat.YELLOW + "Your in " + TextFormat.BOLD + TextFormat.AQUA + getPlugin().getLobby());
                                                } else {
                                                    ((AetherPlayer) player).sendHub(true, title, subTitle);
                                                }
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
