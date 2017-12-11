package Aether;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {

  private final String PREFIX = TextFormat.DARK_GRAY +"["+ TextFormat.AQUA + "Aether" + TextFormat.DARK_GRAY +"]";

  @Override
  public void onEnable(){
    info(TextFormat.GREEN + "has Loaded");
  }

  public void registerEvents(){
    Aether.utils.Utils utils = new Aether.utils.Utils(this);
    Listener[] u = utils.getEvents();
  for(Listener events : u){
	getServer().getPluginManager().registerEvents(events, this);
   }
  }

  public void info(String msg){
    getServer().getLogger().info(PREFIX + " " + msg);
  }
}