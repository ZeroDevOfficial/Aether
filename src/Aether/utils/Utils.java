package Aether.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemMap;
import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;

public class Utils {
	
  private Aether.Main plugin;

  public Utils(Aether.Main main){
    this.setPlugin(main);
  }
  
  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }

  public void killEntiies(String lvl){
    Level level = getPlugin().getServer().getLevelByName(lvl);
  if(level instanceof Level){
	Entity[] entity = level.getEntities();
	getPlugin().info("Deleting " + entity.length +" Entities!");
  for(Entity e : entity){
  if(!(e instanceof Player)){
	e.kill();
   }
  }
    getPlugin().info("There are now " + entity.length +" Entities!");
   }
  }
  
  /** will do this later
  public Aether.games.Game[] getGames(){
    
  }*/

  public Listener[] getEvents(){
    Listener events[] = new Listener[3];
    events[0] = new Aether.events.gadgetEvents(getPlugin());
    events[1] = new Aether.events.playerEvents(getPlugin());
    events[2] = new Aether.events.serverEvents(getPlugin());
    return events;
  }
  
  public VanillaCommand[] getCommands(){
    VanillaCommand cmds[] = new VanillaCommand[2];
    cmds[0] = new Aether.commands.hub(getPlugin(), "hub");
    cmds[1] = new Aether.commands.xyz(getPlugin(), "xyz");
    return cmds;
  }
  
  public Map<String, String> getBossBars(){
   Map<String, String> bossBars = new HashMap<String, String>();
   bossBars.put("hub", TextFormat.DARK_GRAY + "  [ "+ TextFormat.YELLOW +"Your Playing on "+ TextFormat.BOLD.toString() + TextFormat.AQUA + "Aether Network" + TextFormat.RESET + TextFormat.DARK_GRAY +" ]\n\n" + TextFormat.YELLOW + TextFormat.BOLD +"Beta MiniGames Server | {PLAYERS}"+ TextFormat.RESET);
   return bossBars;
  }
  
  public void getHubItems(Player player){
  if(player != null){
    player.getInventory().clearAll();  
	
    Item profile = Item.get(Item.PAPER);
    player.getInventory().setItem(0, profile.setCustomName(TextFormat.YELLOW +"Your Profile.").setLore(TextFormat.AQUA + player.getName() +"\n"+ TextFormat.YELLOW +"This item will display\n- Stats:\n * Kills\n * Deaths\n and more"));
    
    Item games = Item.get(Item.MAGMA_CREAM);
    player.getInventory().setItem(3, games.setCustomName(TextFormat.YELLOW + "Games").setLore(TextFormat.AQUA +"Aether MiniGames\n"+ TextFormat.YELLOW +"Skywars"));    
    
    ItemMap map = (ItemMap) Item.get(Item.MAP);
    player.getInventory().setItem(4, map.setCustomName(TextFormat.YELLOW + "Aether Network"));
  try {
    map.setImage(new File(getPlugin().getDataFolder() + "/images/" + "image.jpg"));
  } catch (final IOException e) {
    getPlugin().info("Could not load map config: read file failed");
  }
    map.sendImage(player);

    Item gadgets = Item.get(Item.REDSTONE_DUST);
    player.getInventory().setItem(5, gadgets.setCustomName(TextFormat.YELLOW + "Gadgets").setLore(TextFormat.RED +"Coming Soon!"));
    
    Item leaper = Item.get(Item.FEATHER);
    player.getInventory().setItem(8, leaper.setCustomName(TextFormat.YELLOW + "Leaper").setLore(TextFormat.AQUA +"Lets you jump around the map."));
   }
  }
 }