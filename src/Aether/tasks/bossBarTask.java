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
    private String currentMessage;

    public bossBarTask(Main main) {
        setPlugin(main);
        length = defaultLength;

        //default message || without this, when the bar spawns, players would see null
        this.currentMessage = TextFormat.DARK_GRAY + " [ " + TextFormat.YELLOW + "Your Playing on " + TextFormat.BOLD.toString() + TextFormat.AQUA + "Aether Network" + TextFormat.RESET + TextFormat.DARK_GRAY + " ]";
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
            tickBossBarMessages();
            if (player.getLevel() == getPlugin().getDefaultLevel()) {
                if (((AetherPlayer) player).currentBossBar != null) {
                    DummyBossBar bossBar = ((AetherPlayer) player).currentBossBar;
                    bossBar.setText(this.currentMessage + TextFormat.RESET);
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

    private void tickBossBarMessages() {
        switch (this.msg) {
            case 0:
                this.currentMessage = TextFormat.YELLOW + "Your Playing on " + TextFormat.BOLD.toString() + TextFormat.AQUA + "Aether Network";
                break;
            case 5:
                this.currentMessage = TextFormat.BOLD.toString() + TextFormat.YELLOW + "Beta MiniGames Server";
                break;
            case 10:
                this.currentMessage = TextFormat.YELLOW + "Players Online: " + TextFormat.AQUA + getPlugin().getServer().getOnlinePlayers().size();
                break;
            case 15:
                this.currentMessage = TextFormat.BOLD.toString() + TextFormat.YELLOW + "Follow us on Twitter" + TextFormat.RESET + TextFormat.AQUA + " @aether_network";
                break;
            case 20:
                this.currentMessage = TextFormat.BOLD.toString() + TextFormat.YELLOW + "Beta " + TextFormat.AQUA + "v" + getPlugin().getDescription().getVersion();
                break;
        }
        if (this.msg > 20) {
            this.msg = 0;
        } else {
            this.msg++;
        }
    }
}
