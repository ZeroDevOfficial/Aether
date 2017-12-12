package Aether.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;

public class playerEvents implements Listener {

  private Aether.Main plugin;
	
  public playerEvents(Aether.Main main){
    this.setPlugin(main);
  }

  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }
  
  @EventHandler
  public void preJoin(cn.nukkit.event.player.PlayerPreLoginEvent event){
    cn.nukkit.Player player = event.getPlayer();
  if(getPlugin().hasLoaded != true) {
	player.kick(getPlugin().getPrefix() + TextFormat.YELLOW +"Server Has Not Loaded yet.", false);
   }
  }
  
  @EventHandler
  public void playerCreation(cn.nukkit.event.player.PlayerCreationEvent e){
    e.setPlayerClass(Aether.AetherPlayer.class);
  }
  
  @EventHandler
  public void join(cn.nukkit.event.player.PlayerJoinEvent event){
	Player player = event.getPlayer();
    event.setJoinMessage("");
    getPlugin().getUtils().getHubItems(player);
  }
  

  @EventHandler
  public void quit(cn.nukkit.event.player.PlayerQuitEvent event){
    event.setQuitMessage("");
  }
}