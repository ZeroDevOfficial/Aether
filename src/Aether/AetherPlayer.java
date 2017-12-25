package Aether;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.network.protocol.LevelEventPacket;
import cn.nukkit.utils.DummyBossBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AetherPlayer extends Player {

    public DummyBossBar currentBossBar;
    public int kills = 0;
    public int deaths = 0;
    public String lastLogin;

    public AetherPlayer(SourceInterface interfaz, Long clientID, String ip, int port) {
        super(interfaz, clientID, ip, port);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy hh:mm a zzz");
        lastLogin = ft.format(date);
    }

    public Main getPlugin() {
        return Aether.Main.getInstance();
    }

    public void sendHub(boolean teleport, String title, String subTitle) {
        if (teleport == true) {
            Level level = getPlugin().getDefaultLevel();
            this.teleport(level.getSafeSpawn());
        }
        this.sendTitle(title, subTitle);
        sendLevelEvent(LevelEventPacket.EVENT_GUARDIAN_CURSE);
        getPlugin().getUtils().getHubItems(this);
        this.setImmobile(false);
    }

    public void sendLevelEvent(int evid) {
        LevelEventPacket pk = new LevelEventPacket();
        pk.evid = evid;
        pk.data = 0;
        pk.x = (float) this.x;
        pk.y = (float) this.y;
        pk.z = (float) this.z;
        this.dataPacket(pk);
    }

    //public boolean saveData() { return true; }//TODO
}
