package Aether;

import java.util.Map;
import java.util.UUID;

import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import cn.nukkit.network.protocol.AddPlayerPacket;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {

  public Map<String, AddPlayerPacket> npcs;
	
  private static Main instance;
  private final String prefix = TextFormat.DARK_GRAY + "[" + TextFormat.AQUA + "Aether Network" + TextFormat.DARK_GRAY + "]" + TextFormat.WHITE;

  @Override
  public void onEnable(){
   instance = this;
   Aether.Startup startup = new Aether.Startup(this);
   startup.load();
  }

  @Override
  public void onDisable(){
    getUtils().killEntiies(getServer().getDefaultLevel().getName());
  }
  
  public void registerNpcs(){
    Map<String, Position> npcsUtils = getUtils().getNpcs();
  for(Map.Entry<String, Position> npc : npcsUtils.entrySet()){
	AddPlayerPacket packet = new AddPlayerPacket();
	packet.uuid = UUID.randomUUID();
	packet.username = npc.getKey();
	packet.entityRuntimeId = 333;
	packet.entityUniqueId = 333;
	packet.x = (float) npc.getValue().x;
	packet.y = (float) npc.getValue().y;
	packet.z = (float) npc.getValue().z;
	packet.yaw = 0;
	packet.pitch = 0;
	packet.item = Item.get(0);
	npcs.put(npc.getKey(), packet);
   }
  }

  public void registerEvents(){
    Listener[] u = getUtils().getEvents();
  for(Listener event : u){
	getServer().getPluginManager().registerEvents(event, this);
   }
  }
  
  public void registerCommands(){
    VanillaCommand[] cmds = getUtils().getCommands();
  for(VanillaCommand cmd : cmds){
    getServer().getCommandMap().register(cmd.getClass().getSimpleName(), cmd);
   }
  }
  
  public Aether.utils.Utils getUtils() {
	return new Aether.utils.Utils(this);
  }
  
  public Aether.entity.entities getEntities(){
	return new Aether.entity.entities(this);
  }
  
  public static Main getInstance(){
    return instance;
  }
  
  public String getPrefix(){
	return prefix;  
  }

  public void info(String msg){
    getServer().getLogger().info(getPrefix() + " " + msg);
  }
}