package Aether;

import Aether.entity.Npc;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main instance;
    private final String prefix = TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Aether Network" + TextFormat.DARK_GRAY + "]" + TextFormat.WHITE;

    public Map<String, Npc> npc = new HashMap<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Aether.Startup startup = new Aether.Startup(this);
        startup.load();
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