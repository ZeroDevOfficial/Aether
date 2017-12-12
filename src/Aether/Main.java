package Aether;

import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {

  private final String prefix = TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Aether Network" + TextFormat.DARK_GRAY + "]" + TextFormat.WHITE;

  @Override
  public void onEnable(){
   Aether.Startup startup = new Aether.Startup(this);
   startup.load();
  }

  public void registerEvents(){
    Listener[] u = getUtils().getEvents();
  for(Listener event : u){
	getServer().getPluginManager().registerEvents(event, this);
	info(TextFormat.AQUA + event.getClass().getSimpleName() + TextFormat.GREEN + " has Been Registered");
   }
  }
  
  public void registerCommands(){
    VanillaCommand[] cmds = getUtils().getCommands();
  for(VanillaCommand cmd : cmds){
    getServer().getCommandMap().register(cmd.getClass().getSimpleName(), cmd);
    info(TextFormat.AQUA + cmd.getClass().getSimpleName() + TextFormat.GREEN + " Command has Been Registered");
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
