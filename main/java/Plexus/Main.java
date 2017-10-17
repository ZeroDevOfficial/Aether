package Plexus;

import cn.nukkit.plugin.PluginBase;

import Plexus.Data.Lang;

public class Main extends PluginBase
{

  /* { function } | plugin enable */ 
  public static onEnable(){
    this.getLogger().info(this.lang().eng("online"));
  }
  
  /* { function } | plugin disable */
  public static onDisable(){
    this.getLogger().info(this.lang().eng("offline"));
  }

  public static lang(){
    return new Lang();
  }
  
}
