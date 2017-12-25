package Aether.utils;

import Aether.Main;
import Aether.entity.Npc;
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
import io.netty.util.internal.ThreadLocalRandom;

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

    public void killEntiies(String lvl) {
        Level level = getPlugin().getServer().getLevelByName(lvl);
        if (level instanceof Level) {
            Entity[] entity = level.getEntities();
            getPlugin().info("Deleting " + entity.length + " Entities!");
            for (Entity e : entity) {
                if (!(e instanceof Player)) {
                    e.kill();
                }
            }
            getPlugin().info("There are now " + entity.length + " Entities!");
        }
    }

    /**
     * will do this later
     * public Aether.games.Game[] getGames(){
     * <p>
     * }
     */

    public Listener[] getEvents() {
        Listener events[] = new Listener[3];
        events[0] = new Aether.events.gadgetEvents(getPlugin());
        events[1] = new Aether.events.playerEvents(getPlugin());
        events[2] = new Aether.events.serverEvents(getPlugin());
        return events;
    }

    public VanillaCommand[] getCommands() {
        VanillaCommand cmds[] = new VanillaCommand[2];
        cmds[0] = new Aether.commands.hub(getPlugin(), "hub");
        cmds[1] = new Aether.commands.xyz(getPlugin(), "xyz");
        return cmds;
    }

    public Map<String, String> getBossBars() {
        Map<String, String> bossBars = new HashMap<String, String>();
        bossBars.put("hub", TextFormat.DARK_GRAY + " [ " + TextFormat.YELLOW + "Your Playing on " + TextFormat.BOLD.toString() + TextFormat.AQUA + "Aether Network" + TextFormat.RESET + TextFormat.DARK_GRAY + " ]\n\n" + TextFormat.YELLOW + TextFormat.BOLD + "Beta MiniGames Server | {PLAYERS}" + TextFormat.RESET);
        return bossBars;
    }

    public Map<String, Npc> getNpcs() {
        Map<String, Npc> npcs = new HashMap<String, Npc>();
        npcs.put("Test", new Npc(getPlugin(), new Location(35, 151, 44, 320, -10, getPlugin().getDefaultLevel()), "Test", Integer.toString(ThreadLocalRandom.current().nextInt(1, 2 + 1))));
        npcs.put("Test2", new Npc(getPlugin(), new Location(43, 151, 44, 50, -10, getPlugin().getDefaultLevel()), "Test2", Integer.toString(ThreadLocalRandom.current().nextInt(3, 4 + 1))));
        npcs.put("Test3", new Npc(getPlugin(), new Location(43, 151, 52, 135, -10, getPlugin().getDefaultLevel()), "Test3", Integer.toString(ThreadLocalRandom.current().nextInt(5, 6 + 1))));
        npcs.put("Test4", new Npc(getPlugin(), new Location(35, 151, 52, 225, -10, getPlugin().getDefaultLevel()), "Test4", Integer.toString(ThreadLocalRandom.current().nextInt(7, 8 + 1))));
        return npcs;
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

            Item gadgets = Item.get(Item.REDSTONE_DUST);
            player.getInventory().setItem(5, gadgets.setCustomName(TextFormat.YELLOW + "Gadgets").setLore(TextFormat.RED + "Coming Soon!").setCustomBlockData(new CompoundTag().putString("HubItem", "gadgets")));

            Item leaper = Item.get(Item.FEATHER);
            player.getInventory().setItem(8, leaper.setCustomName(TextFormat.YELLOW + "Leaper").setLore(TextFormat.AQUA + "Lets you jump around the map.").setCustomBlockData(new CompoundTag().putString("HubItem", "leaper")));
        }
    }
}