package Plexus;

import cn.nukkit.plugin.PluginBase;

import Plexus.Data.Lang;

public class Main extends PluginBase
{

  /* { function } | plugin enable */ 
  public void onEnable(){
    this.getLogger().info(new Lang().eng("online"));
  }
  
  /* { function } | plugin disable */
  public void onDisable(){
    this.getLogger().info(new Lang().eng("offline"));
  }

  
}
