package Aether.events;

import Aether.AetherPlayer;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.DummyBossBar;

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
  public void playerCreation(cn.nukkit.event.player.PlayerCreationEvent e){
    e.setPlayerClass(Aether.AetherPlayer.class);
  }
  
  @EventHandler
  public void join(cn.nukkit.event.player.PlayerJoinEvent event){
	Player player = event.getPlayer();
    event.setJoinMessage("");
    new Aether.tasks.sendHub((Player) player, false).runTaskLater(getPlugin(), 30);

    ((AetherPlayer) player).currentBossBar = new DummyBossBar.Builder(player).text(getPlugin().getUtils().getBossBars().get("hub").replace("{PLAYERS}", "Online 0")).length(100).build();
    player.createBossBar(((AetherPlayer) player).currentBossBar);

    player.setCheckMovement(false);
  }
  
  @EventHandler
  public void damage(cn.nukkit.event.entity.EntityDamageEvent event){
    Entity entity = event.getEntity();
  if(entity instanceof Player){
  if(entity.getLevel() != null && entity.getLevel() == getPlugin().getServer().getDefaultLevel()){
	event.setCancelled(true);
    }
   }
  }

  @EventHandler
  public void blockBreak(cn.nukkit.event.block.BlockBreakEvent event){
    Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getServer().getDefaultLevel()){
	//event.setCancelled(true);
   }
  }

  @EventHandler
  public void blockPlace(cn.nukkit.event.block.BlockPlaceEvent event){
    Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getServer().getDefaultLevel()){
	//event.setCancelled(true);
   }
  }

  @EventHandler
  public void hunger(cn.nukkit.event.player.PlayerFoodLevelChangeEvent event){
	Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getServer().getDefaultLevel()){
    event.setCancelled(true);
   }
  }

  @EventHandler
  public void quit(cn.nukkit.event.player.PlayerQuitEvent event){
    event.setQuitMessage("");
    Player player = event.getPlayer();
    ((AetherPlayer) player).saveData();
  }
}