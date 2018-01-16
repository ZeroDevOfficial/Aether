package Aether.utils;

import Aether.Main;
import Aether.commands.*;
import Aether.events.*;
import Aether.tasks.*;
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

    public String[] maps = new String[]{
            "hub",
            "dragonRun"
    };
    public VanillaCommand[] commands = new VanillaCommand[]{
            new clear("clear"),
            new dimension("dimension"),
            new hub("hub"),
            new xyz("xyz")
    };
    public Listener[] listeners = new Listener[]{
            new entityEvents(),
            new gadgetEvents(),
            new lobbyEvents(),
            new playerEvents(),
            new serverEvents()
    };
    public handlerTask[] tasks = new handlerTask[]{
            new borderTask(),
            new bossBarTask()
    };
    
    //Settings
    public boolean modifyWorld = true;

    private Main getPlugin() {
        return Main.getInstance();
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
                if (player.getDisplayName().contains("andrep0617") || player.getDisplayName().contains("ZeroDevOfficial")) {
                    map.setImage(new File(getPlugin().getServerFolder() + "/images/yes/" + "yes.png"));
                } else {
                    map.setImage(new File(getPlugin().getServerFolder() + "/images/" + "icon.jpg"));
                }
            } catch (final IOException e) {
                getPlugin().info("Could not load map config: read file failed");
            }
            map.sendImage(player);

            Item gadgets = Item.get(Item.COMPASS);
            player.getInventory().setItem(5, gadgets.setCustomName(TextFormat.YELLOW + "Lobby Selector").setLore(TextFormat.RED + "Switch Between multiple lobbies.").setCustomBlockData(new CompoundTag().putString("HubItem", "lobbies")));

            Item leaper = Item.get(Item.RABBIT_FOOT);
            player.getInventory().setItem(8, leaper.setCustomName(TextFormat.YELLOW + "Leaper").setLore(TextFormat.AQUA + "Lets you jump around the map.").setCustomBlockData(new CompoundTag().putString("HubItem", "leaper")));
        }
    }

    public Map<String, Location> getTexts() {
        Map<String, Location> texts = new HashMap<>();
        texts.put("Text1 {Coming Soon}", new Location(56, 113.90, 38, getPlugin().getDefaultLevel()));
        texts.put("Text2 {Coming Soon}", new Location(56, 113.90, 42, getPlugin().getDefaultLevel()));
        texts.put("Text3 {Coming Soon}", new Location(56, 113.90, 46, getPlugin().getDefaultLevel()));
        texts.put("Text4 {Coming Soon}", new Location(56, 113.90, 50, getPlugin().getDefaultLevel()));

        texts.put("MiniGames", new Location(51, 109, 44, getPlugin().getDefaultLevel()));
        texts.put(TextFormat.YELLOW + "Tap an Npc to join a game.", new Location(51, 109.50, 44, getPlugin().getDefaultLevel()));
        return texts;
    }

    public Map<String, Location> getNpcs() {
        Map<String, Location> npcs = new HashMap<>();
        npcs.put("Test1", new Location(56, 111, 38, 90, 15, getPlugin().getDefaultLevel()));
        npcs.put("Test2", new Location(56, 111, 42, 90, 15, getPlugin().getDefaultLevel()));
        npcs.put("Test3", new Location(56, 111, 46, 90, 15, getPlugin().getDefaultLevel()));
        npcs.put("Test4", new Location(56, 111, 50, 90, 15, getPlugin().getDefaultLevel()));

        npcs.put("Lola", new Location(-17, 112, 44, 270, 0, getPlugin().getDefaultLevel()));
        npcs.put("Zak", new Location(-18, 112, 43, 180, 0, getPlugin().getDefaultLevel()));
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
