package Aether;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {

  public boolean hasLoaded = false;
  private final String prefix = TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Aether" + TextFormat.DARK_GRAY + "]" + TextFormat.WHITE;

  @Override
  public void onEnable(){
    registerEvents();
    info(TextFormat.GREEN + "has Loaded");
    hasLoaded = true;
  }

  public void registerEvents(){
    Listener[] u = getUtils().getEvents();
  for(Listener event : u){
	getServer().getPluginManager().registerEvents(event, this);
	info(TextFormat.AQUA + event.getClass().getSimpleName() + TextFormat.GREEN + " Has Been Registered");
   }
  }
  
  public Aether.utils.Utils getUtils() {
	return new Aether.utils.Utils(this);
  }
  
  public String getPrefix(){
	return prefix;  
  }

  public void info(String msg){
    getServer().getLogger().info(getPrefix() + " " + msg);
  }
}