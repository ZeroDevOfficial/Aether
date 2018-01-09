package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.utils.DummyBossBar;
import cn.nukkit.utils.TextFormat;

public class bossBarTask extends handlerTask {

    private final int defaultLength = 5;
    private Main plugin;
    private int length;

    private int msg = 0;
    private String currentMessage;

    public bossBarTask(Main main) {
        super(main);
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
    public void tick() {
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
        String aether = TextFormat.GRAY + "Your Playing on " + TextFormat.AQUA + "Aether Network";
        switch (this.msg) {
            case 0:
                this.currentMessage = aether + "\n\n" + TextFormat.GRAY + "      Beta MiniGames Server";
                break;
            case 5:
                this.currentMessage = aether + "\n\n" + TextFormat.GRAY + "         Players Online: " + TextFormat.AQUA + getPlugin().getServer().getOnlinePlayers().size();
                break;
            case 10:
                this.currentMessage = aether + "\n\n" + TextFormat.GRAY + "   Follow us " + TextFormat.AQUA + "@aether_network";
                break;
        }
        if (this.msg > 10) {
            this.msg = 0;
        } else {
            this.msg++;
        }
    }
}
