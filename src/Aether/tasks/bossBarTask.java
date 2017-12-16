package Aether.tasks;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;

public class bossBarTask extends Task {

  private Main plugin;

  public bossBarTask(Main main) {
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
  if(player.getLevel() == getPlugin().getServer().getDefaultLevel()){
  if(((AetherPlayer) player).currentBossBar != null){
    ((AetherPlayer) player).currentBossBar.setText(getPlugin().getUtils().getBossBars().get("hub").replace("{PLAYERS}", "Online: "+ TextFormat.AQUA + getPlugin().getServer().getOnlinePlayers().size()));
     }
    }
   }
  }
}
