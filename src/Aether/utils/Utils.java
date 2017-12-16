package Aether.utils;

import java.util.HashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
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

  public Map<String, Position> getNpcs(){
    Map<String, Position> npc = new HashMap<String, Position>();
    npc.put("Game1", new Position(36.50, 151, 45.50, getPlugin().getServer().getDefaultLevel()));
    npc.put("Game2", new Position(42.50, 151, 45.50, getPlugin().getServer().getDefaultLevel()));
    npc.put("Game3", new Position(42.50, 151, 51.50, getPlugin().getServer().getDefaultLevel()));
    npc.put("Game4", new Position(36.50, 151, 51.50, getPlugin().getServer().getDefaultLevel()));
    return npc;
  }
  
  public Map<String, String> getBossBars(){
   Map<String, String> bossBars = new HashMap<String, String>();
   bossBars.put("hub", TextFormat.DARK_GRAY + "  [ "+ TextFormat.YELLOW +"Your Playing on "+ TextFormat.BOLD.toString() + TextFormat.AQUA + "Aether Network" + TextFormat.RESET + TextFormat.DARK_GRAY +" ]\n\n" + TextFormat.YELLOW + TextFormat.BOLD +"Beta MiniGames Server | {PLAYERS}"+ TextFormat.RESET);
   return bossBars;
  }
  
  public void getHubItems(Player player){
    player.getInventory().clearAll();  
	
    Item profile = Item.get(Item.PAPER);
    profile.setCustomName(TextFormat.YELLOW +"Your Profile.");
    profile.setLore(TextFormat.AQUA + player.getName() +"\n"+ TextFormat.YELLOW +"This item will display\n- Stats:\n * Kills\n * Deaths\n and more");
    player.getInventory().setItem(0, profile);
	
    Item games = Item.get(Item.MAGMA_CREAM);
    games.setCustomName(TextFormat.YELLOW + "Games");
    games.setLore(TextFormat.AQUA +"Aether MiniGames\n"+ TextFormat.YELLOW +"No Current games Available");
    player.getInventory().setItem(3, games);

    Item gadgets = Item.get(Item.REDSTONE_DUST);
    gadgets.setCustomName(TextFormat.YELLOW + "Gadgets");
    gadgets.setLore(TextFormat.RED +"Coming Soon!");
    player.getInventory().setItem(4, gadgets);
	
    Item lobbies = Item.get(Item.EMPTY_MAP);
    lobbies.setCustomName(TextFormat.YELLOW + "Lobbies");
    lobbies.setLore(TextFormat.RED +"More Lobbies Coming Soon!");
    player.getInventory().setItem(5, lobbies);
    
    Item leaper = Item.get(Item.FEATHER);
    leaper.setCustomName(TextFormat.YELLOW + "Leaper");
    leaper.setLore(TextFormat.AQUA +"Lets you jump around the map.");
    player.getInventory().setItem(8, leaper);
  }
 }