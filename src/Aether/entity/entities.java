package Aether.entity;

import cn.nukkit.Player;

public class entities {
	
  public Aether.Main plugin;
  public int npcs = 0;
  
  public entities(Aether.Main main){
    this.setPlugin(main);
  }

  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }

  public void addNpc(Player player){
  }
}
