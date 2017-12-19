package Aether;

import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Level;
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

  public static Main getInstance(){
    return instance;
  }

  public String getPrefix(){
	return prefix;  
  }
  
  public Level getDefaultLevel(){
    return getServer().getLevelByName("hub");
  }

  public void info(String msg){
    getServer().getLogger().info(getPrefix() + " " + msg);
  }
}