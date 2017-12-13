package Aether.tasks;

import Aether.AetherPlayer;
import cn.nukkit.Player;
import cn.nukkit.scheduler.NukkitRunnable;

public class sendHub extends NukkitRunnable {

  private Player player;
  private boolean teleport;

  public sendHub(Player player, boolean teleport){ 
    this.player = player;
    this.teleport = teleport;
  }
	
  @Override
  public void run(){
	((AetherPlayer) this.player).sendHub(teleport);
  }
}
