package Aether.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;

public class playerEvents implements Listener {

  private Aether.Main plugin;
	
  public playerEvents(Aether.Main plugin){
    this.plugin = plugin;
  }
  
  public Aether.Main getPlugin(){
    return this.plugin;
  }
  
  @EventHandler
  public void preJoin(cn.nukkit.event.player.PlayerPreLoginEvent event){
    cn.nukkit.Player player = event.getPlayer();
  if(getPlugin().hasLoaded != true) {
	player.kick(getPlugin().getPrefix() + TextFormat.YELLOW +"Server Has Not Loaded yet.", false);
   }
  }
  
  @EventHandler
  public void join(cn.nukkit.event.player.PlayerJoinEvent event){
    event.setJoinMessage("");
  }
  
  @EventHandler
  public void quit(cn.nukkit.event.player.PlayerQuitEvent event){
    event.setQuitMessage("");
  }
}