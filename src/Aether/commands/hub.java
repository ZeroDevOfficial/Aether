package Aether.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.utils.TextFormat;

public class hub extends VanillaCommand {

  private Aether.Main plugin;

  public hub(Aether.Main main, String name){
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
	((Player) player).getInventory().clearAll();
    new Aether.tasks.sendHub((Player) player, true).runTaskLater(getPlugin(), 20);
  } else {
	player.sendMessage(TextFormat.RED +"You can only use /hub in-game!");  
  }
    return true;
  }
}