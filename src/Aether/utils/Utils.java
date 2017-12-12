package Aether.utils;

import java.io.File;
import java.io.IOException;

import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemMap;
import cn.nukkit.item.enchantment.Enchantment;
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
  for(Entity e : entity){
  if(!(e instanceof Player)){
	e.kill();
     }
    }
   }
  }
  
  /** will do this later
  public Aether.games.Game[] getGames(){
    
  }*/

  public Listener[] getEvents(){
    Listener events[] = new Listener[2];
    events[0] = new Aether.events.playerEvents(getPlugin());
    events[1] = new Aether.events.serverEvents(getPlugin());
    return events;
  }

  public VanillaCommand[] getCommands(){
    VanillaCommand cmds = new VanillaCommand[1];
    cmds[0] = new Aether.commands.hub(getPlugin(), "hub");
    return cmds;
  }
  
  public ItemMap getMap(String img){
    final ItemMap map = new ItemMap();
  try {
    map.setImage(new File(getPlugin().getDataFolder() + "/images/" + img));
  } catch (IOException e){
    getPlugin().info(TextFormat.RED + "Could not create map image!");
  }
    return map;
  }
  
  public void getHubItems(Player player){
	player.getInventory().clearAll();  
	
	Item profile = Item.get(Item.PAPER);
	profile.setCustomName(TextFormat.YELLOW +"Your Profile.");
	profile.addEnchantment(Enchantment.getEnchantment(1));
	player.getInventory().setItem(0, profile);
	
	Item games = Item.get(Item.COMPASS);
	games.setCustomName(TextFormat.YELLOW + "Games");
	games.addEnchantment(Enchantment.getEnchantment(1));
	player.getInventory().setItem(3, games);

	Item icon = getPlugin().getUtils().getMap("icon.jpg");
	icon.setCustomName(TextFormat.YELLOW + "Aether Network");
	icon.addEnchantment(Enchantment.getEnchantment(1));
	player.getInventory().setItem(4, icon);
	
	Item leaper = Item.get(Item.FEATHER);
	leaper.setCustomName(TextFormat.YELLOW + "Leaper");
	leaper.addEnchantment(Enchantment.getEnchantment(1));
	player.getInventory().setItem(8, leaper);
  }
 }
