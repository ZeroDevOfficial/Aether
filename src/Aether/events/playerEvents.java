package Aether.events;

import java.util.HashMap;
import java.util.Map;

import Aether.AetherPlayer;
import Aether.tasks.end;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.TextFormat;

public class playerEvents implements Listener {

  private Aether.Main plugin;
  public Map<String, Object> endTask;
  public Map<String, Object> spawnTask;
	
  public playerEvents(Aether.Main main){
    this.setPlugin(main);
    this.endTask = new HashMap<String, Object>();
    this.spawnTask = new HashMap<String, Object>();
  }

  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }
  
  @EventHandler
  public void playerCreation(cn.nukkit.event.player.PlayerCreationEvent event){
    event.setPlayerClass(Aether.AetherPlayer.class);
  }
  
  @EventHandler
  public void join(cn.nukkit.event.player.PlayerJoinEvent event){
	Player player = event.getPlayer();
    event.setJoinMessage("");
    player.addEffect(new Effect(14, "Invisibility", 0, 0, 0).setDuration(100000));
    player.addEffect(new Effect(15, "Blindness", 0, 0, 0).setDuration(100000).setAmplifier(20));
    player.setImmobile(true);
    //player.setCanClimbWalls(true);
    player.setCheckMovement(false);
    player.sendMessage(TextFormat.YELLOW +"Loading... Please Wait!");
    endTask.put(player.getName(), new end(player, getPlugin(), this));
    ((NukkitRunnable) endTask.get(player.getName())).runTaskLater(getPlugin(), 200);
  }

  @EventHandler
  public void damage(cn.nukkit.event.entity.EntityDamageEvent event){
    Entity entity = event.getEntity();
  if(entity instanceof Player){
  if(entity.getLevel() != null && entity.getLevel() == getPlugin().getDefaultLevel()){
	event.setCancelled(true);
  }
  if(entity.getLevel() != null && entity.getLevel() == getPlugin().getServer().getLevelByName("temp")){
    event.setCancelled(true);
    }
   }
  }

  @EventHandler
  public void blockBreak(cn.nukkit.event.block.BlockBreakEvent event){
    Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getDefaultLevel()){
	event.setCancelled(true);
   }
  }

  @EventHandler
  public void blockPlace(cn.nukkit.event.block.BlockPlaceEvent event){
    Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getDefaultLevel()){
	event.setCancelled(true);
   }
  }

  @EventHandler
  public void hunger(cn.nukkit.event.player.PlayerFoodLevelChangeEvent event){
	Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getDefaultLevel()){
    event.setCancelled(true);
   }
  }

  @EventHandler
  public void quit(cn.nukkit.event.player.PlayerQuitEvent event){
    event.setQuitMessage("");
    Player player = event.getPlayer();
    ((AetherPlayer) player).saveData();
  if(endTask.containsKey(player.getName())){
    ((NukkitRunnable) endTask.get(player.getName())).cancel();
  }
  if(spawnTask.containsKey(player.getName())){
    ((NukkitRunnable) spawnTask.get(player.getName())).cancel();
   }
  }
}