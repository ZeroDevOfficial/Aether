package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;

public class bossBarTask extends Task {

    private final int defaultLength = 5;
    private Main plugin;
    private int length;

    private int msg = 0;

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
                        String bossBarText = getPlugin().getUtils().getBossBars().get("hub");
                        bossBar.setText(bossBarText.replace("{MSG}", getBossBarMessages() + TextFormat.RESET));
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

    private String getBossBarMessages() {
        String currentMessage = TextFormat.BOLD + " Beta MiniGames Server" + TextFormat.RESET + TextFormat.DARK_GRAY + " | " + TextFormat.YELLOW + "Online: " + TextFormat.AQUA + "0";
        switch (this.msg) {
            case 0:
                currentMessage = TextFormat.BOLD + " Beta MiniGames Server" + TextFormat.RESET + TextFormat.DARK_GRAY + " | " + TextFormat.YELLOW + "Online: " + TextFormat.AQUA + getPlugin().getServer().getOnlinePlayers().size();
                this.msg++;
                break;
            case 1:
                currentMessage = " Follow us on Twitter" + TextFormat.RESET + TextFormat.AQUA + " @aether_network";
                this.msg++;
                break;
            case 2:
                currentMessage = " Follow us on Twitter" + TextFormat.RESET + TextFormat.AQUA + " @aether_network";
                this.msg = 0;
                break;
        }
        return currentMessage;
    }
}
