package Aether;

import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {

  private static Main instance;
  private final String prefix = TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Aether Network" + TextFormat.DARK_GRAY + "]" + TextFormat.WHITE;

  @Override
  public void onEnable(){
   instance = this;
   Aether.Startup startup = new Aether.Startup(this);
   startup.load();
  }

  @Override
  public void onDisable(){
    getUtils().killEntiies(getServer().getDefaultLevel().getName());
  }

  public void registerEvents(){
    Listener[] u = getUtils().getEvents();
  for(Listener event : u){
	getServer().getPluginManager().registerEvents(event, this);
   }
  }
  
  public void registerCommands(){
    VanillaCommand[] cmds = getUtils().getCommands();
  for(VanillaCommand cmd : cmds){
    getServer().getCommandMap().register(cmd.getClass().getSimpleName(), cmd);
   }
  }
  
  public Aether.utils.Utils getUtils(){
	return new Aether.utils.Utils(this);
  }

  public Aether.entity.entities getEntities(){
	return new Aether.entity.entities(this);
  }

  public static Main getInstance(){
    return instance;
  }

  public String getPrefix(){
	return prefix;  
  }

  public void info(String msg){
    getServer().getLogger().info(getPrefix() + " " + msg);
  }
}