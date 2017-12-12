package Aether.events;

import cn.nukkit.event.Listener;

public class serverEvents implements Listener {

 private Aether.Main plugin;
		
  public serverEvents(Aether.Main main){
    this.setPlugin(main);
  }

  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }
}
