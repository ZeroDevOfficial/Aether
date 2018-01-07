package Aether;

import Aether.entity.FloatingText;
import Aether.entity.Npc;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.Map;

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
        getPlugin().getServer().loadLevel("Oldhub");

        Level level = getPlugin().getDefaultLevel();
        level.setRaining(false);
        level.setThundering(false);

        registerCommands();
        registerEvents();
        registerNpcs();
        registerTexts();

        getPlugin().getServer().getScheduler().scheduleRepeatingTask(new Aether.tasks.bossBarTask(getPlugin()), 20);
        getPlugin().getServer().getScheduler().scheduleRepeatingTask(new Aether.tasks.borderTask(getPlugin()), 10);

        getPlugin().getServer().getNetwork().setName(TextFormat.BOLD + getPlugin().getPrefix() + TextFormat.DARK_GRAY);

        getPlugin().info(TextFormat.GREEN + "has Loaded");
    }


    public void registerCommands() {
        VanillaCommand[] commands = getPlugin().getUtils().getCommands();
        for (VanillaCommand cmd : commands) {
            getPlugin().getServer().getCommandMap().register(cmd.getClass().getSimpleName(), cmd);
        }
    }

    public void registerEvents() {
        Listener[] events = getPlugin().getUtils().getEvents();
        for (Listener event : events) {
            getPlugin().getServer().getPluginManager().registerEvents(event, getPlugin());
        }
    }

    public void registerNpcs() {
        Map<String, Location> npcs = getPlugin().getUtils().getNpcs();
        for (Map.Entry<String, Location> npc : npcs.entrySet()) {
            getPlugin().npc.put(npc.getKey(), new Npc(getPlugin(), npc.getValue(), npc.getKey(), new Skin(new File(getPlugin().getDataFolder() + "/skins/" + npc.getKey() + ".png"))));
        }
    }

    public void registerTexts() {
        Map<String, Location> texts = getPlugin().getUtils().getTexts();
        for (Map.Entry<String, Location> text : texts.entrySet()) {
            getPlugin().texts.put(text.getKey(), new FloatingText(getPlugin(), text.getValue(), text.getKey()));
            if (getPlugin().texts.get(text.getKey()).getName().contains("Text")) {
                getPlugin().texts.get(text.getKey()).setText("Coming Soon");
            }
        }
    }
}
