package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import Aether.entity.FloatingText;
import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

public class welcomeTextTask extends handlerTask {

    private Main plugin;

    public welcomeTextTask(Main main) {
        super(main);
        setPlugin(main);
    }

    public Main getPlugin() {
        return plugin;
    }

    public void setPlugin(Aether.Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void tick() {
        if (getPlugin().getDefaultLevel().getPlayers().size() != 0) {
            for (FloatingText text : getPlugin().texts.values()) {
                for (Player player : getPlugin().getDefaultLevel().getPlayers().values()) {
                    if(((AetherPlayer) player).dimension == 2) {
                        switch (text.getName()) {
                            case "{Welcome1}":
                                text.setText(TextFormat.DARK_GRAY + "==============================");
                                if (player.getPosition().distance(text.getPos()) <= 10) {
                                    text.sendText(player);
                                }
                                break;
                            case "{Welcome2}":
                                text.setText(TextFormat.YELLOW + "Welcome to " + TextFormat.AQUA + TextFormat.BOLD + "Aether Network " + TextFormat.YELLOW + player.getName());
                                if (player.getPosition().distance(text.getPos()) <= 10) {
                                    text.sendText(player);
                                }
                                break;
                            case "{Welcome3}":
                                text.setText(TextFormat.YELLOW + "You are in Lobby " + TextFormat.AQUA + getPlugin().lobby);
                                if (player.getPosition().distance(text.getPos()) <= 10) {
                                    text.sendText(player);
                                }
                                break;
                            case "{Welcome4}":
                                text.setText(TextFormat.YELLOW + "There are " + TextFormat.AQUA + getPlugin().getServer().getOnlinePlayers().size() + TextFormat.YELLOW + " Player(s) Currently online");
                                if (player.getPosition().distance(text.getPos()) <= 10) {
                                    text.sendText(player);
                                }
                                break;
                            case "{Welcome5}":
                                text.setText(TextFormat.DARK_GRAY + "==============================");
                                break;
                        }
                    } else {
                        text.despawnFrom(player);
                    }
                }
            }
        }
    }
}
