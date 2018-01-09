package Aether;

import Aether.entity.FloatingText;
import Aether.entity.Npc;
import cn.nukkit.AdventureSettings;
import cn.nukkit.Player;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.level.Level;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.network.protocol.PlayerListPacket;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.DummyBossBar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AetherPlayer extends Player {

    public DummyBossBar currentBossBar;
    public int kills = 0;
    public int deaths = 0;
    public String lastLogin;
    public int dimension = 0;

    public AetherPlayer(SourceInterface interfaz, Long clientID, String ip, int port) {
        super(interfaz, clientID, ip, port);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy hh:mm a zzz");
        lastLogin = ft.format(date);
    }

    private Main getPlugin() {
        return Aether.Main.getInstance();
    }

    public void setupForTeleport() {
        this.getInventory().clearAll();
        this.getInventory().sendContents(this);
        this.getInventory().sendArmorContents(this);

        this.addEffect(new Effect(Effect.INVISIBILITY, "Invisibility", 0, 0, 0).setDuration(100000));
        this.addEffect(new Effect(Effect.BLINDNESS, "Blindness", 0, 0, 0).setDuration(100000));

        this.setImmobile(true);
        this.setEnableClientCommand(false);
        this.setCheckMovement(false);
    }

    public void sendHub(boolean teleport, String title, String subTitle) {
        new NukkitRunnable() {
            @Override
            public void run() {
                if (!getPlayer().isOnline()) {
                    this.cancel();
                } else {
                    if (teleport) {
                        Level level = getPlugin().getDefaultLevel();
                        getPlayer().teleport(level.getSafeSpawn());
                    }
                    getPlayer().setGamemode(2);
                    getPlayer().sendTitle(title, subTitle);

                    new NukkitRunnable() {
                        @Override
                        public void run() {
                            if (!getPlayer().isOnline()) {
                                this.cancel();
                            } else {
                                getPlayer().removeAllEffects();
                                getPlayer().setEnableClientCommand(true);

                                getPlugin().getUtils().getHubItems(getPlayer());

                                for (Npc npc : getPlugin().npc.values()) {
                                    npc.spawnTo(getPlayer());
                                }

                                for (FloatingText text : getPlugin().texts.values()) {
                                    text.despawnFrom(getPlayer());
                                    text.sendText(getPlayer());
                                }

                                getConfig();
                                createBossBar("");

                                getPlayer().setImmobile(false);
                                setDefaultAdventureSettings();

                                playSound(55);
                            }
                        }
                    }.runTaskLater(getPlugin(), 10);
                }
            }
        }.runTaskLater(getPlugin(), 30);
    }

    public Config getConfig(){
        String folder = new File(getPlugin().getServer().getDataPath()).getParent();
        Config config = new Config(folder + "/PlayerData/" + getPlayer().getName().toLowerCase() + ".json", Config.JSON);
        return config;
    }

    public void setDefaultAdventureSettings() {//will add the commented ones later.
        this.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
        //this.getAdventureSettings().set(AdventureSettings.Type.WORLD_BUILDER, false);
        //this.getAdventureSettings().set(AdventureSettings.Type.BUILD_AND_MINE, false);
        //this.getAdventureSettings().set(AdventureSettings.Type.DOORS_AND_SWITCHED, false);
        this.getAdventureSettings().update();
    }

    public void createBossBar(String text) {
        if (currentBossBar != null) {
            currentBossBar.destroy();
        }
        ((AetherPlayer) getPlayer()).currentBossBar = new DummyBossBar.Builder(getPlayer()).text(text).length(100).build();
        getPlayer().createBossBar(((AetherPlayer) getPlayer()).currentBossBar);
    }

    public void setCape(String name) {
        Skin skin = new Skin(this.getSkin().getData());
        skin.setCape(skin.new Cape(new File(getPlugin().getDataFolder() + "/images/" + name + "_cape.png"), skin.getModel()));

        PlayerListPacket pk = new PlayerListPacket();
        pk.type = PlayerListPacket.TYPE_ADD;
        pk.entries = new PlayerListPacket.Entry[]{
                new PlayerListPacket.Entry(this.getUniqueId(), this.getId(), this.getName(), skin)
        };

        this.setSkin(skin);
        this.dataPacket(pk);
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
