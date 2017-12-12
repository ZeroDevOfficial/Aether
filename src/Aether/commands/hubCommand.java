package Aether.commands;

import Aether.AetherPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.utils.TextFormat;

public class hubCommand extends VanillaCommand {

  private Aether.Main plugin;

  public hubCommand(Aether.Main main, String name){
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
    ((AetherPlayer) player).sendHub();
  } else {
	player.sendMessage(TextFormat.RED +"You can only use /hub in-game!");  
  }
    return true;
  }
}
