package Aether.events;

import Aether.AetherPlayer;
import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.DummyBossBar;
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
  public void playerCreation(cn.nukkit.event.player.PlayerCreationEvent e){
    e.setPlayerClass(Aether.AetherPlayer.class);
  }
  
  @EventHandler
  public void join(cn.nukkit.event.player.PlayerJoinEvent event){
	Player player = event.getPlayer();
    event.setJoinMessage("");
    new Aether.tasks.sendHub((Player) player, false).runTaskLater(getPlugin(), 30);

    ((AetherPlayer) player).currentBossBar = new DummyBossBar.Builder(player).text(TextFormat.DARK_GRAY + "[ "+ TextFormat.YELLOW +"Your Playing on "+ TextFormat.BOLD.toString() + TextFormat.AQUA + "Aether Network" + TextFormat.DARK_GRAY +" ]" + TextFormat.RESET.toString()).length(100).build();
    player.createBossBar(((AetherPlayer) player).currentBossBar);
  }

  @EventHandler
  public void blockBreak(cn.nukkit.event.block.BlockBreakEvent event){
    Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getServer().getDefaultLevel()){
	event.setCancelled(true);
   }
  }

  @EventHandler
  public void blockPlace(cn.nukkit.event.block.BlockPlaceEvent event){
    Player player = event.getPlayer();
  if(player.getLevel() == getPlugin().getServer().getDefaultLevel()){
	event.setCancelled(true);
   }
  }
  
  @EventHandler
  public void move(cn.nukkit.event.player.PlayerMoveEvent event){
    Player player = event.getPlayer();
  if(player instanceof Player){
  if(event.getTo().distance(event.getFrom()) > 0.2){
  if(player.getLevel().getBlock(new Vector3(player.x, player.y - 1, player.z)).getId() == Block.REDSTONE_BLOCK){
    player.knockBack(player, 0, player.getDirectionVector().x, player.getDirectionVector().z, 1);
    player.getLevel().addSound(new cn.nukkit.level.sound.EndermanTeleportSound(player));
    player.sendPopup(TextFormat.GREEN + "Boosted!");
     }
    }
   }
  }

  @EventHandler
  public void quit(cn.nukkit.event.player.PlayerQuitEvent event){
    event.setQuitMessage("");
    Player player = event.getPlayer();
    ((AetherPlayer) player).saveData();
  }
}