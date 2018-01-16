package Aether.commands;

import Aether.AetherPlayer;
import Aether.Main;
import Aether.tasks.changeDimensionTask;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;

public class dimension extends VanillaCommand {

    public dimension(String name) {
        super(name);
    }

    private Main getPlugin() {
        return Main.getInstance();
    }

    @Override
    public boolean execute(CommandSender player, String alias, String[] args) {
        if (player instanceof Player) {
            if ((args.length > 0)) {
                if ((Integer.parseInt(args[0]) == 0) || (Integer.parseInt(args[0]) == 1) || (Integer.parseInt(args[0]) == 2)) {
                    if (((AetherPlayer) player).currentBossBar != null) {
                        ((AetherPlayer) player).currentBossBar.destroy();
                    }
                    Level level = ((Player) player).getLevel();
                    if (((Player) player).getLevel().getName() != "temp") {//if they are in this level it would crash them.
                        ((Player) player).teleport(getPlugin().getServer().getLevelByName("temp").getSafeSpawn());
                    }
                    new changeDimensionTask(((Player) player), Integer.parseInt(args[0]), level.getName()).runTaskLater(getPlugin(), 10);
                } else {
                    player.sendMessage("Please type /dimension (int)\ndimension types: [ 0 = overworld || 1 = nether || 2 = end ]");
                }
            } else {
                player.sendMessage("Please type /dimension (int)\ndimension types: [ 0 = overworld || 1 = nether || 2 = end ]");
            }
        } else {
            player.sendMessage(TextFormat.RED + "You can only run this command in-game!");
        }
        return true;
    }
}
