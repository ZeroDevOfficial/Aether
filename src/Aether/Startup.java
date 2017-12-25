package Aether;

import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;

public class Startup {

    private Aether.Main plugin;

    public Startup(Aether.Main main) {
        this.setPlugin(main);
    }

    private Aether.Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    public void load() {
        getPlugin().getServer().loadLevel("hub");

        Level level = getPlugin().getServer().getDefaultLevel();
        level.setTime(14000);
        level.stopTime();
        level.checkTime();
        level.setRaining(false);

        getPlugin().registerEvents();
        getPlugin().registerCommands();

        getPlugin().getServer().getScheduler().scheduleRepeatingTask(new Aether.tasks.bossBarTask(getPlugin()), 20);
        getPlugin().getServer().getScheduler().scheduleRepeatingTask(new Aether.tasks.borderTask(getPlugin()), 10);

        getPlugin().getServer().getNetwork().setName(TextFormat.BOLD + getPlugin().getPrefix() + TextFormat.DARK_GRAY);

        getPlugin().info(TextFormat.GREEN + "has Loaded");
    }
}
