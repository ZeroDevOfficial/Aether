package Aether;

import Aether.entity.FloatingText;
import Aether.entity.Npc;
import Aether.tasks.handlerTask;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.TextFormat;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main instance;
    private final String prefix = TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Aether Network" + TextFormat.DARK_GRAY + "]" + TextFormat.WHITE;

    public int lobby = 0;
    public Map<String, Npc> npc = new HashMap<>();
    public Map<String, FloatingText> texts = new HashMap<>();
    public Map<String, handlerTask> tasks = new HashMap<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Aether.Startup startup = new Aether.Startup(this);
        startup.load();

        Config config = new Config(getDataFolder() + "/config.json", Config.JSON, new ConfigSection("lobby", 0));

        /** *
         * This is so I can drop the core into a server and it will become
         * Its own lobby || pretty dumb but I need a way to do multi lobby rn.
         */
        this.lobby = config.getInt("lobby");
    }

    public Aether.utils.Utils getUtils() {
        return new Aether.utils.Utils(this);
    }

    public String getPrefix() {
        return prefix;
    }

    public Level getDefaultLevel() {
        return getServer().getLevelByName("hub");
    }

    public void info(String msg) {
        getServer().getLogger().info(getPrefix() + " " + msg);
    }
}