package Aether.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.potion.Effect;
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

            ((Player) player).addEffect(new Effect(Effect.INVISIBILITY, "Invisibility", 0, 0, 0).setDuration(100000));

            ((Player) player).setImmobile(true);
            ((Player) player).setEnableClientCommand(true);

            ((Player) player).getInventory().clearAll();
            ((Player) player).teleport(getPlugin().getDefaultLevel().getSafeSpawn());

            new Aether.tasks.sendHub((Player) player, false, TextFormat.YELLOW + "Welcome to spawn", TextFormat.AQUA + player.getName()).runTaskLater(getPlugin(), 20);
        } else {
            player.sendMessage(TextFormat.RED + "You can only run this command in-game!");
        }
        return true;
    }
}
