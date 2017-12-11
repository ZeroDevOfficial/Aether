package Aether.utils;

import cn.nukkit.event.Listener;

public class Utils {
	
  private Aether.Main plugin;
		
  public Utils(Aether.Main plugin){
    this.plugin = plugin;
  }
  
  public Aether.Main getPlugin(){
    return this.plugin;
  }
  
  /** will do this later
  public Aether.games.Game[] getGames(){
    
  }*/

  public Listener[] getEvents(){
    Listener events[] = new Listener[0];
    events[0] = new Aether.events.playerEvents(getPlugin());
    return events;
  }
}