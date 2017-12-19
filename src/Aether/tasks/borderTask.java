package Aether.tasks;
import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;

public class borderTask extends Task {

  private Main plugin;

  public borderTask(Main main) {
    setPlugin(main);
  }

  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }

  @Override
  public void onRun(int arg0){
  for(Player player : getPlugin().getServer().getOnlinePlayers().values()){
  if(player != null){
  if(player.getLevel() == getPlugin().getDefaultLevel()){
  if(player.getPosition().distance(getPlugin().getDefaultLevel().getSafeSpawn()) >= 70){
    ((Player) player).setImmobile(true);
    ((Player) player).getInventory().clearAll();
    ((Player) player).teleport(getPlugin().getDefaultLevel().getSafeSpawn());
    ((AetherPlayer) player).sendHub(false, TextFormat.RED +"Woah", TextFormat.RED +"You can't leave spawn silly :)");
      }
     }
    }
   }
  }
}
