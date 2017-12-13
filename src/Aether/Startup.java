package Aether;

import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;

public class Startup {

  private Aether.Main plugin;

  public Startup(Aether.Main main) {
    this.setPlugin(main);
  }

  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }

  public void load(){
    getPlugin().registerEvents();
    getPlugin().registerCommands();
    getPlugin().getServer().getNetwork().setName(TextFormat.BOLD + getPlugin().getPrefix() + TextFormat.DARK_GRAY);
    
    Level level = getPlugin().getServer().getDefaultLevel();
    level.setTime(14000);
    level.stopTime = true;
    level.setRaining(false);
    level.setRainTime(10000);
    
    getPlugin().info(TextFormat.GREEN + "has Loaded");
  }
}
