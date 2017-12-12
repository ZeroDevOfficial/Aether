package Aether;

import cn.nukkit.Player;
import cn.nukkit.network.SourceInterface;

public class AetherPlayer extends Player {

   public AetherPlayer(SourceInterface interfaz, Long clientID, String ip, int port){
   super(interfaz, clientID, ip, port);
   }
}
