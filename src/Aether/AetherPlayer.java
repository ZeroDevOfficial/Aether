package Aether;

import Aether.entity.FloatingText;
import Aether.entity.Npc;
import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.scheduler.NukkitRunnable;
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

    private Main getPlugin() {
        return Aether.Main.getInstance();
    }

    public void sendHub(boolean teleport, String title, String subTitle) {
        new NukkitRunnable() {
            @Override
            public void run() {
                if (teleport) {
                    Level level = getPlugin().getDefaultLevel();
                    getPlayer().teleport(level.getSafeSpawn());
                }

                getPlayer().sendTitle(title, subTitle);

                new NukkitRunnable() {
                    @Override
                    public void run() {

                        getPlayer().removeAllEffects();
                        getPlayer().setEnableClientCommand(true);

                        getPlugin().getUtils().getHubItems(getPlayer());

                        for (Npc npc : getPlugin().npc.values()) {
                            npc.despawnFrom(getPlayer());
                            npc.spawnTo(getPlayer());
                        }

                        for (FloatingText text : getPlugin().texts.values()) {
                            text.despawnFrom(getPlayer());
                            text.spawnTo(getPlayer());
                        }

                        getPlayer().setImmobile(false);
                        getPlayer().getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
                        getPlayer().getAdventureSettings().update();

                        playSound(55);

                    }
                }.runTaskLater(getPlugin(), 10);
            }
        }.runTaskLater(getPlugin(), 30);
    }

    public void playSound(int sound) {
        LevelSoundEventPacket pk = new LevelSoundEventPacket();
        pk.sound = sound;
        pk.x = (float) this.x;
        pk.y = (float) this.y;
        pk.z = (float) this.z;
        pk.extraData = -1;
        pk.pitch = 1;

        this.dataPacket(pk);
    }

    //public boolean saveData() { return true; }//TODO
}
