package Aether.events;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

public class gadgetEvents implements Listener {
	
  private Main plugin;

  public gadgetEvents(Main main) {
    setPlugin(main);
  }
  
  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }
  
  @EventHandler
  public void interact(cn.nukkit.event.player.PlayerInteractEvent event){
    Player player = event.getPlayer();
    Item item = event.getItem();
  if(item.getCustomName().contains("Your Profile.")){
	FormWindowSimple window = new FormWindowSimple("Your Profile", 
      TextFormat.YELLOW +"Info:\n"+
	  TextFormat.YELLOW +"- Display Name: "+ TextFormat.AQUA + player.getDisplayName() +"\n"+
      TextFormat.YELLOW +"- Kills: "+ TextFormat.AQUA + ((AetherPlayer) player).kills +"\n"+ 
      TextFormat.YELLOW +"- Deaths: "+ TextFormat.AQUA + ((AetherPlayer) player).deaths +"\n"+ 
      TextFormat.YELLOW +"- Last Login: "+ TextFormat.AQUA + ((AetherPlayer) player).lastLogin +"\n");
	window.addButton(new ElementButton("Ok."));
	player.showFormWindow(window);
  }
  if(item.getCustomName().contains("Games")){
    FormWindowSimple window = new FormWindowSimple("Games", "Click a MiniGame");
    window.addButton(new ElementButton("Cancel", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
    window.addButton(new ElementButton(TextFormat.GRAY +"Skywars", new ElementButtonImageData("url", "https://i.imgur.com/SfYBMNl.png")));
    player.showFormWindow(window);
  }
  if(item.getCustomName().contains("Leaper")){
    double x = player.getDirectionVector().x;
    double z = player.getDirectionVector().z;
    player.knockBack(player, 0, x, z, 0.85);
    player.sendPopup(TextFormat.GREEN +"Leaped!");
   }
  }
}
