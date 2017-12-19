package Aether.tasks;

import Aether.Main;
import Aether.events.playerEvents;
import cn.nukkit.Player;
import cn.nukkit.network.protocol.ChangeDimensionPacket;
import cn.nukkit.scheduler.NukkitRunnable;

public class end extends NukkitRunnable {

  private Player player;
  private Main plugin;
  private playerEvents playerEvents;

  public end(Player player, Main main, playerEvents playerEvent){
   this.player = player;
   this.plugin = main;
   this.playerEvents = playerEvent;
  }

  public Main getPlugin(){
    return this.plugin;
  }

  @Override
  public void run(){
  if(this.player != null){
    ChangeDimensionPacket pk = new ChangeDimensionPacket();
    pk.dimension = 2;
    pk.x = (float) player.x;
    pk.y = (float) player.y;
    pk.z = (float) player.z;
    pk.respawn = false;
    this.player.dataPacket(pk);
    this.playerEvents.spawnTask.put(this.player.getName(), new Aether.tasks.spawn((Player) player, getPlugin()));
    ((NukkitRunnable) this.playerEvents.spawnTask.get(this.player.getName())).runTaskLater(getPlugin(), 60);
  } else {
    this.cancel();
   }
  }
}
