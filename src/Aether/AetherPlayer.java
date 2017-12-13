package Aether;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.network.protocol.LevelEventPacket;

public class AetherPlayer extends Player {

  public AetherPlayer(SourceInterface interfaz, Long clientID, String ip, int port){
  super(interfaz, clientID, ip, port);
  }
   
  public Main getPlugin() {
    return Aether.Main.getInstance();
  }

  public void sendHub(boolean teleport){
  if(teleport == true){
	Level level = getServer().getDefaultLevel();
    this.teleport(level.getSafeSpawn());
  }
    sendLevelEvent(LevelEventPacket.EVENT_GUARDIAN_CURSE);
    getPlugin().getUtils().getHubItems(this);
  }
  
  public void sendLevelEvent(int evid){
    LevelEventPacket pk = new cn.nukkit.network.protocol.LevelEventPacket();
    pk.evid = evid;
    pk.data = 0;
	pk.x = (float) this.x;
	pk.y = (float) this.y;
	pk.z = (float) this.z;
	this.dataPacket(pk);
  }
}
