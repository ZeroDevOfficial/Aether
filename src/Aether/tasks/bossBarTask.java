package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;

public class bossBarTask extends Task {

    private Main plugin;
    private int length;
    private final int defaultLength = 5;

    public bossBarTask(Main main) {
        setPlugin(main);
        length = defaultLength;
    }

    public Aether.Main getPlugin() {
        return plugin;
    }

    public void setPlugin(Aether.Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onRun(int arg0) {
        for (Player player : getPlugin().getServer().getOnlinePlayers().values()) {
            if (player != null) {
                if (player.getLevel() == getPlugin().getDefaultLevel()) {
                    if (((AetherPlayer) player).currentBossBar != null) {
                        DummyBossBar bossBar = ((AetherPlayer) player).currentBossBar;
                        bossBar.setText(getPlugin().getUtils().getBossBars().get("hub").replace("{PLAYERS}", "Online: " + TextFormat.AQUA + getPlugin().getServer().getOnlinePlayers().size()));
                        length += defaultLength;
                        if (length == 100) {
                            length = defaultLength;
                        }
                        bossBar.reshow();
                        bossBar.setLength(length);
                    }
                }
            }
        }
    }
}
