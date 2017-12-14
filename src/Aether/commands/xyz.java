package Aether.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;

public class xyz extends VanillaCommand {

  private Aether.Main plugin;

  public xyz(Aether.Main main, String name){
  super(name);
    this.setPlugin(main);
  }
  
  public Aether.Main getPlugin() {
    return plugin;
  }

  public void setPlugin(Aether.Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean execute(CommandSender player, String alias, String[] args){	
  if(player instanceof Player){
    player.sendMessage("Your xyz is "+ ((Vector3) player).x +", "+ ((Vector3) player).y +", "+ ((Vector3) player).z);
  } else {
	player.sendMessage(TextFormat.RED +"You can only use /hub in-game!");  
  }
    return true;
  }
}
