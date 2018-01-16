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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AetherPlayer extends Player {

    public DummyBossBar currentBossBar;
    public int kills = 0;
    public int deaths = 0;
    public String lastLogin;
    public int dimension = 0;

    public AetherPlayer(SourceInterface interfaz, Long clientID, String ip, int port) {
        super(interfaz, clientID, ip, port);
    }

    private Main getPlugin() {
        return Main.getInstance();
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

                                kills = getConfig().getInt("kills");
                                deaths = getConfig().getInt("deaths");
                                lastLogin = getConfig().getString("last_login");

                                createBossBar("");

                                getPlayer().setImmobile(false);
                                setDefaultAdventureSettings(getPlugin().getUtils().modifyWorld);

                                playSound(55);
                            }
                        }
                    }.runTaskLater(getPlugin(), 10);
                }
            }
        }.runTaskLater(getPlugin(), 30);
    }

    public Config getConfig() {
        ConfigSection cs = new ConfigSection();
        cs.set("display_name", this.getName());
        cs.set("kills", this.kills);
        cs.set("deaths", this.deaths);
        cs.set("last_login", this.lastLogin);
        Config config = new Config(getPlugin().getServerFolder() + "/PlayerData/" + getPlayer().getName().toLowerCase() + ".json", Config.JSON, cs);
        return config;
    }

    private void setDefaultAdventureSettings(boolean build) {
        this.getAdventureSettings().set(AdventureSettings.Type.ALLOW_FLIGHT, true);
        this.getAdventureSettings().set(AdventureSettings.Type.WORLD_BUILDER, build);
        this.getAdventureSettings().set(AdventureSettings.Type.BUILD_AND_MINE, build);
        this.getAdventureSettings().set(AdventureSettings.Type.DOORS_AND_SWITCHED, true);
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
        skin.setCape(skin.new Cape(new File(getPlugin().getServerFolder() + "/images/" + name + "_cape.png"), skin.getModel()));

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


    public void saveSkin() {
        final byte[] byteArray = this.getSkin().getData();
        final BufferedImage finalSkin = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        final int entireByteArray = byteArray.length;
        int start = 0;
        int x = 0;
        int y = 0;
        while (entireByteArray > start) {
            if (x == 64) {
                x = 0;
                y++;
            }
            final int r = byteArray[start] & 0xFF;
            final int g = byteArray[start + 1] & 0xFF;
            final int b = byteArray[start + 2] & 0xFF;
            final int a = byteArray[start + 3] & 0xFF;

            finalSkin.setRGB(x, y, new Color(r, g, b, a).getRGB());
            start = start + 4;
            x++;

        }
        try {
            ImageIO.write(finalSkin, "png",
                    new File(getPlugin().getServerFolder() + "/images/Skins/player/", this.getName() + ".png"));
        } catch (final IOException e1) {
            e1.printStackTrace();
        }
    }

    //public boolean saveData() { return true; }//TODO
}
