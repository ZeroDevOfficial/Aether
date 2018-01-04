package Aether.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;

import java.text.DecimalFormat;

public class xyz extends VanillaCommand {

    private Aether.Main plugin;

    public xyz(Aether.Main main, String name) {
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
            DecimalFormat decimal = new DecimalFormat("##.##");

            player.sendMessage(String.format("Your xyz is %s, %s, %s\nYour yaw and pitch is: %s, %s", decimal.format(((Vector3) player).x), decimal.format(((Vector3) player).y), decimal.format(((Vector3) player).z), decimal.format(((Location) player).getYaw()), decimal.format(((Location) player).getPitch())));
        } else {
            player.sendMessage(TextFormat.RED + "You can only use /hub in-game!");
        }
        return true;
    }
}
