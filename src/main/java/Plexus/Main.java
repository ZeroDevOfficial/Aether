package Plexus;

import cn.nukkit.plugin.PluginBase;

import Plexus.Data.Lang;

public class Main extends PluginBase
{

  /* { function } | plugin enable */ 
  public void onEnable(){
    this.getLogger().info(this.lang().eng("online"));
  }
  
  /* { function } | plugin disable */
  public void onDisable(){
    this.getLogger().info(this.lang().eng("offline"));
  }

  
}
