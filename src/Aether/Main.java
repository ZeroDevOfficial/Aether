package Aether;

import Aether.entity.FloatingText;
import Aether.entity.Npc;
import Aether.tasks.handlerTask;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main instance;
    private final String prefix = TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Aether Network" + TextFormat.DARK_GRAY + "]" + TextFormat.WHITE;

    public Config config;
    public Map<String, Npc> npc = new HashMap<>();
    public Map<String, FloatingText> texts = new HashMap<>();
    public Map<String, handlerTask> tasks = new HashMap<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("lobby", "Lobby 0");
        map.put("players", "60");//TODO add update player count
        this.config = new Config(getDataFolder() + "/config.json", Config.JSON, new ConfigSection(map));

        new Aether.Startup().load();
    }

    public String getLobby() {
        return this.config.getString("lobby");
    }

    public String getServerFolder() {
        return new File(getServer().getDataPath()).getParent() + "/ServerData";
    }

    public Aether.utils.Utils getUtils() {
        return new Aether.utils.Utils();
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