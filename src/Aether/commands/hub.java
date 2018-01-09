package Aether.commands;

import Aether.AetherPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.utils.TextFormat;

public class hub extends VanillaCommand {

    private Aether.Main plugin;

    public hub(Aether.Main main, String name) {
        super(name);
        this.setPlugin(main);
    }

    public Aether.Main getPlugin() {
        return plugin;
    }

    public void setPlugin(Aether.Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender player, String alias, String[] args) {
        if (player instanceof Player) {

            ((AetherPlayer) player).setupForTeleport();
            if (((Player) player).getLevel() != getPlugin().getDefaultLevel()) {
                ((Player) player).teleport(((Player) player).getLevel().getSafeSpawn());
                new Aether.tasks.sendHub((Player) player, false, TextFormat.YELLOW + "Welcome to spawn", TextFormat.AQUA + player.getName()).runTaskLater(getPlugin(), 10);
            } else {
                ((Player) player).teleport(getPlugin().getDefaultLevel().getSafeSpawn());
                ((AetherPlayer) player).sendHub(false, TextFormat.YELLOW + "Welcome to spawn", TextFormat.AQUA + player.getName());
            }
        } else {
            player.sendMessage(TextFormat.RED + "You can only run this command in-game!");
        }
        return true;
    }
}
