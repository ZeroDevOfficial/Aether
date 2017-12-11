package Aether.events;

import cn.nukkit.event.Listener;

public class playerEvents implements Listener {

  private Aether.Main plugin;
	
  public playerEvents(Aether.Main plugin){
    this.plugin = plugin;
  }
  
  public Aether.Main getPlugin(){
    return this.plugin;
  }
}
