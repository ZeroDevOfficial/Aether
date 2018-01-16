package Aether;

import Aether.entity.FloatingText;
import Aether.entity.Npc;
import Aether.tasks.handlerTask;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.Map;

public class Startup {

    private Main getPlugin() {
        return Main.getInstance();
    }

    public void load() {

        for (String lvl : getPlugin().getUtils().maps) {
            getPlugin().getServer().loadLevel(lvl);
            getPlugin().getServer().getLevelByName(lvl).setRaining(false);
            getPlugin().getServer().getLevelByName(lvl).setThundering(false);
        }

        for (VanillaCommand cmd : getPlugin().getUtils().commands) {
            getPlugin().getServer().getCommandMap().register(cmd.getName(), cmd);
        }

        for (Listener listener : getPlugin().getUtils().listeners) {
            getPlugin().getServer().getPluginManager().registerEvents(listener, getPlugin());
        }

        for (handlerTask task : getPlugin().getUtils().tasks) {
            getPlugin().tasks.put(task.toString(), task);
        }

        Map<String, Location> npcs = getPlugin().getUtils().getNpcs();
        for (Map.Entry<String, Location> npc : npcs.entrySet()) {
            getPlugin().npc.put(npc.getKey(), new Npc(npc.getValue(), npc.getKey(), new Skin(new File(getPlugin().getServerFolder() + "/images/Skins/" + npc.getKey() + ".png"))));
        }

        Map<String, Location> texts = getPlugin().getUtils().getTexts();
        for (Map.Entry<String, Location> text : texts.entrySet()) {
            getPlugin().texts.put(text.getKey(), new FloatingText(text.getValue(), text.getKey()));
            if (getPlugin().texts.get(text.getKey()).getName().contains("{Coming Soon}")) {
                getPlugin().texts.get(text.getKey()).setText("Coming Soon");
            }
        }

        getPlugin().getServer().getScheduler().scheduleRepeatingTask(new Aether.tasks.handlerTask(), 20);
        getPlugin().getServer().getNetwork().setName(TextFormat.BOLD + getPlugin().getPrefix() + TextFormat.DARK_GRAY);
        getPlugin().info(TextFormat.GREEN + "has Loaded");
    }
}
