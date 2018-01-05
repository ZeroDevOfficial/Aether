package Aether.utils;

import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemMap;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private Aether.Main plugin;

    public Utils(Aether.Main main) {
        this.setPlugin(main);
    }

    private Aether.Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    public Map<String, String> getBossBars() {
        Map<String, String> bossBars = new HashMap<>();
        bossBars.put("hub", "{MSG}" + TextFormat.RESET);
        return bossBars;
    }

    public VanillaCommand[] getCommands() {
        VanillaCommand cmds[] = new VanillaCommand[3];
        cmds[0] = new Aether.commands.clearinv(getPlugin(), "clearinv");
        cmds[1] = new Aether.commands.hub(getPlugin(), "hub");
        cmds[2] = new Aether.commands.xyz(getPlugin(), "xyz");
        return cmds;
    }

    public Listener[] getEvents() {
        Listener events[] = new Listener[4];
        events[0] = new Aether.events.entityEvents(getPlugin());
        events[1] = new Aether.events.gadgetEvents(getPlugin());
        events[2] = new Aether.events.playerEvents(getPlugin());
        events[3] = new Aether.events.serverEvents(getPlugin());
        return events;
    }

    public void getHubItems(Player player) {
        if (player != null) {
            player.getInventory().clearAll();

            Item profile = Item.get(Item.PAPER);
            player.getInventory().setItem(0, profile.setCustomName(TextFormat.YELLOW + "Your Profile.").setLore(TextFormat.YELLOW + "This item will display\n- Stats:\n * Kills\n * Deaths\n and more").setCustomBlockData(new CompoundTag().putString("HubItem", "profile")));

            Item games = Item.get(Item.MAGMA_CREAM);
            player.getInventory().setItem(3, games.setCustomName(TextFormat.YELLOW + "Games").setLore(TextFormat.AQUA + "Aether MiniGames\n" + TextFormat.YELLOW + "Skywars").setCustomBlockData(new CompoundTag().putString("HubItem", "games")));

            ItemMap map = (ItemMap) Item.get(Item.MAP);
            player.getInventory().setItem(4, map.setCustomName(TextFormat.YELLOW + "Aether Network"));
            try {
                if (player.getDisplayName().contains("andrep0617") || player.getDisplayName().contains("EpicSteve33")) {
                    map.setImage(new File(getPlugin().getDataFolder() + "/images/yes/" + "yes.png"));
                } else {
                    map.setImage(new File(getPlugin().getDataFolder() + "/images/" + "icon.jpg"));
                }
            } catch (final IOException e) {
                getPlugin().info("Could not load map config: read file failed");
            }
            map.sendImage(player);

            Item gadgets = Item.get(Item.COMPASS);
            player.getInventory().setItem(5, gadgets.setCustomName(TextFormat.YELLOW + "Lobby Selector").setLore(TextFormat.RED + "Switch Between multiple lobbies.").setCustomBlockData(new CompoundTag().putString("HubItem", "lobbies")));

            Item leaper = Item.get(Item.FEATHER);
            player.getInventory().setItem(8, leaper.setCustomName(TextFormat.YELLOW + "Leaper").setLore(TextFormat.AQUA + "Lets you jump around the map.").setCustomBlockData(new CompoundTag().putString("HubItem", "leaper")));
        }
    }

    public Map<String, Location> getTexts() {
        Map<String, Location> texts = new HashMap<>();
        texts.put("Text1", new Location(56, 111, 38, getPlugin().getDefaultLevel()));
        texts.put("Text2", new Location(56, 111, 42, getPlugin().getDefaultLevel()));
        texts.put("Text4", new Location(56, 111, 46, getPlugin().getDefaultLevel()));
        texts.put("Text3", new Location(56, 111, 50, getPlugin().getDefaultLevel()));

        texts.put("MiniGames", new Location(51, 109, 44, getPlugin().getDefaultLevel()));
        texts.put("Welcome", new Location(37, 109, 42, getPlugin().getDefaultLevel()));
        return texts;
    }

    public Map<String, Location> getNpcs() {
        Map<String, Location> npcs = new HashMap<>();
        npcs.put("Test1", new Location(56, 111, 38, 90, 15, getPlugin().getDefaultLevel()));
        npcs.put("Test2", new Location(56, 111, 42, 90, 15, getPlugin().getDefaultLevel()));
        npcs.put("Test3", new Location(56, 111, 46, 90, 15, getPlugin().getDefaultLevel()));
        npcs.put("Test4", new Location(56, 111, 50, 90, 15, getPlugin().getDefaultLevel()));
        return npcs;
    }

    public void killEntiies(String lvl) {
        if (getPlugin().getServer().isLevelLoaded(lvl)) {
            Level level = getPlugin().getServer().getLevelByName(lvl);
            Entity[] entities = level.getEntities();
            getPlugin().info("Deleting " + entities.length + " Entities!");
            for (Entity e : entities) {
                if (!(e instanceof Player)) {
                    e.kill();
                }
            }
            getPlugin().info("There are now " + entities.length + " Entities!");
        }
    }
}