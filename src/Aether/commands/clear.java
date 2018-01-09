package Aether.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.utils.TextFormat;

public class clear extends VanillaCommand {

    private Aether.Main plugin;

    public clear(Aether.Main main, String name) {
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
            if (((Player) player).getLevel() == getPlugin().getDefaultLevel()) {
                if (player.isOp()) {
                    ((Player) player).getInventory().clearAll();
                    ((Player) player).getInventory().sendContents(((Player) player));
                    ((Player) player).getInventory().sendArmorContents(((Player) player));
                } else {
                    player.sendMessage(TextFormat.RED + "Admin only command!");
                }
            } else {
                player.sendMessage(TextFormat.RED + "Sorry you can only use this in the hub!");
            }
        } else {
            player.sendMessage(TextFormat.RED + "You can only run this command in-game!");
        }
        return true;
    }
}
