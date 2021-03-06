package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

public class borderTask extends handlerTask {

    private Main getPlugin() {
        return Main.getInstance();
    }

    @Override
    public void tick() {
        for (Player player : getPlugin().getServer().getOnlinePlayers().values()) {
            if (player != null) {
                if (player.getLevel() == getPlugin().getDefaultLevel()) {
                    if (player.getPosition().distance(getPlugin().getDefaultLevel().getSafeSpawn()) >= 120 || player.y <= 64) {
                        ((AetherPlayer) player).setupForTeleport();
                        player.teleport(getPlugin().getDefaultLevel().getSafeSpawn());

                        ((AetherPlayer) player).sendHub(false, TextFormat.RED + "Woah", TextFormat.RED + "You can't leave spawn silly :)");
                    }
                }
            }
        }
    }
}
